package com.evaluation.presenter

import io.reactivex.disposables.CompositeDisposable

interface BasePresenter <T: BaseView> {

    var view: T?

    val compositeDisposable: CompositeDisposable

    fun bindView(view: T)

    fun end ()

}