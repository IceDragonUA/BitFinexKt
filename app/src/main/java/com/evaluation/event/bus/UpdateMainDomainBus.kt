package com.evaluation.event.bus

import com.evaluation.dagger.main.MainScope
import com.evaluation.event.UpdateEvent
import javax.inject.Inject

@MainScope
class UpdateMainDomainBus @Inject constructor() : DomainBus() {

    private val updateEvent = createEventPublisher<UpdateEvent>()

    fun updateEvent() = updateEvent

    fun postUpdated() = updateEvent.onNext(UpdateEvent())

}
