package com.evaluation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.evaluation.R
import com.evaluation.adapter.CustomListAdapter.ListAdapterHolder
import com.evaluation.model.TickerItem
import kotlinx.android.synthetic.main.list_item.view.*
import kotlin.properties.Delegates

class CustomListAdapter(private val onItemSelectedListener: ((TickerItem) -> Unit)? = null) : RecyclerView.Adapter<ListAdapterHolder>() {

    var mTickerList: List<TickerItem> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ListAdapterHolder =
        ListAdapterHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))

    override fun onBindViewHolder(listAdapterHolder: ListAdapterHolder, position: Int) {
        listAdapterHolder.bind(getItem(position), onItemSelectedListener)
    }

    private fun getItem(position: Int): TickerItem {
        return mTickerList[position]
    }

    override fun getItemCount(): Int {
        return mTickerList.size
    }

    class ListAdapterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(tickerItem: TickerItem, onItemSelectedListener: ((TickerItem) -> Unit)?) {
            itemView.setOnClickListener { onItemSelectedListener?.invoke(tickerItem) }
            itemView.name.text = tickerItem.mSymbol
            itemView.numberBid.text = tickerItem.mBid.toString()
            itemView.numberAsk.text = tickerItem.mAsk.toString()
            itemView.numberAvg.text = ((tickerItem.mBid + tickerItem.mAsk) / 2).toString()
        }
    }
}