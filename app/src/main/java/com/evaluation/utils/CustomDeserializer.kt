package com.evaluation.utils

import com.evaluation.model.TickerItem
import com.evaluation.model.TickerList
import com.google.gson.*
import java.lang.reflect.Type


/**
 * @author Vladyslav Havrylenko
 * @since 02.05.2020
 */
class CustomDeserializer : JsonDeserializer<TickerList?> {

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext): TickerList {
        val arrayElement = json.asJsonArray
        return if (arrayElement.isJsonArray) {
            val list: ArrayList<TickerItem> = arrayListOf()
            arrayElement.forEach {
                if (((it as JsonArray).get(0).asString).contains("t")) {
                    list.add(
                        TickerItem(
                            mSymbol = it.get(0).asString,
                            mBid = it.get(1).asFloat,
                            mBidSize = it.get(2).asFloat,
                            mAsk = it.get(3).asFloat,
                            mAskSize = it.get(4).asFloat,
                            mDailyChange = it.get(5).asFloat,
                            mDailyChangeRelative = it.get(6).asFloat,
                            mLastPrice = it.get(7).asFloat,
                            mVolume = it.get(8).asFloat,
                            mHigh = it.get(9).asFloat,
                            mLow = it.get(10).asFloat
                        )
                    )
                }
            }
            TickerList(list)
        } else {
            throw JsonParseException("Unsupported type of monument element")
        }
    }
}