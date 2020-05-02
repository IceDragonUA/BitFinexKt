package com.evaluation.fragment.detail

import com.evaluation.event.UpdateEvent
import com.evaluation.event.bus.UpdateDetailDomainBus
import com.evaluation.model.TickerItem
import com.evaluation.presenter.BasePresenterImpl
import com.evaluation.repository.detail.DetailRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * @author Vladyslav Havrylenko
 * @since 15.04.2020
 */
class DetailPresenter @Inject constructor(
    private val detailRepository: DetailRepository,
    private val updateDomainBus: UpdateDetailDomainBus
) : DetailContract.Presenter, BasePresenterImpl<DetailContract.View>() {

    private var symbol: String? = null

    override fun bindView(view: DetailContract.View) {
        super.bindView(view)
        detailRepository.syncRegisterData(UPDATE_INTERVAL)
    }

    override fun provideData(symbol: String) {
        this.symbol = symbol
        compositeDisposable.addAll(
            detailRepository
                .getTickerList(symbol)
                .doOnSubscribe { view?.showLoading() }
                .subscribe(::onLoadingCompletion, ::onLoadingError),

            updateDomainBus.updateEvent()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::onGetSuccess, ::onGetError)
        )
    }

    override fun refreshData() {
        compositeDisposable.add(
            detailRepository
                .getTickerList(symbol)
                .doOnSubscribe { view?.showLoading() }
                .subscribe(::onLoadingCompletion, ::onLoadingError)
        )
    }

    private fun onLoadingCompletion(list: List<TickerItem>) {
        view?.hideLoading()
        view?.showDetail(list)
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