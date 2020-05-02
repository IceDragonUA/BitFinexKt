package com.evaluation.event.bus

import com.evaluation.event.Event
import io.reactivex.subjects.PublishSubject

open class DomainBus {

    fun <T: Event> createEventPublisher (): PublishSubject<T>
            = PublishSubject.create()

}