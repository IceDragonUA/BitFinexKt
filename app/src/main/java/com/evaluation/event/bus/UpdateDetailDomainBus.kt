package com.evaluation.event.bus

import com.evaluation.dagger.detail.DetailScope
import com.evaluation.dagger.main.MainScope
import com.evaluation.event.UpdateEvent
import javax.inject.Inject

@DetailScope
class UpdateDetailDomainBus @Inject constructor() : DomainBus() {

    private val updateEvent = createEventPublisher<UpdateEvent>()

    fun updateEvent() = updateEvent

    fun postUpdated() = updateEvent.onNext(UpdateEvent())

}
