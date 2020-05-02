package com.evaluation.fragment.main

import com.evaluation.event.UpdateEvent
import com.evaluation.event.bus.UpdateMainDomainBus
import com.evaluation.model.TickerItem
import com.evaluation.presenter.BasePresenterImpl
import com.evaluation.repository.main.MainRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * @author Vladyslav Havrylenko
 * @since 15.04.2020
 */
class MainPresenter @Inject constructor(
    private val mainRepository: MainRepository,
    private val updateDomainBus: UpdateMainDomainBus
) : MainContract.Presenter, BasePresenterImpl<MainContract.View>() {

    override fun bindView(view: MainContract.View) {
        super.bindView(view)
        mainRepository.syncRegisterData(UPDATE_INTERVAL)
    }

    override fun provideData() {
        compositeDisposable.addAll(
            mainRepository
                .getTickerList()
                .doOnSubscribe { view?.showLoading() }
                .subscribe(::onLoadingCompletion, ::onLoadingError),

            updateDomainBus.updateEvent()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::onGetSuccess, ::onGetError)
        )
    }

    override fun refreshData() {
        compositeDisposable.add(
            mainRepository
                .getTickerList()
                .doOnSubscribe { view?.showLoading() }
                .subscribe(::onLoadingCompletion, ::onLoadingError)
        )
    }

    private fun onLoadingCompletion(list: List<TickerItem>) {
        view?.hideLoading()
        view?.showList(list.sortedBy { it.mSymbol })
    }

    private fun onLoadingError(error: Throwable) {
        view?.hideLoading()
    }

    private fun onGetSuccess(event: UpdateEvent) {
        refreshData()
    }

    private fun onGetError(t: Throwable) {

    }

    companion object {
        private const val UPDATE_INTERVAL = 5
    }
}