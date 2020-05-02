package com.evaluation.fragment.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.evaluation.App
import com.evaluation.R
import com.evaluation.adapter.CustomListAdapter
import com.evaluation.model.TickerItem
import kotlinx.android.synthetic.main.main_layout.*
import javax.inject.Inject

/**
 * @author Vladyslav Havrylenko
 * @since 09.03.2020
 */
class DetailFragment : Fragment(), DetailContract.View {

    @Inject
    lateinit var presenter: DetailContract.Presenter

    private lateinit var adapter: CustomListAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.detailComponent?.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.info_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.bindView(this)
        presenter.provideData(DetailFragmentArgs.fromBundle(requireArguments()).mSymbol)
        initView()
    }

    private fun initView() {
        adapter = CustomListAdapter {}
        listView.layoutManager = LinearLayoutManager(context)
        listView.itemAnimator = DefaultItemAnimator()
        listView.adapter = adapter
    }

    override fun showDetail(tickerItem: List<TickerItem>) {
        adapter.mTickerList = tickerItem
    }

    override fun showLoading() {}

    override fun hideLoading() {}

    override fun onDestroyView() {
        presenter.end()
        super.onDestroyView()
    }

    override fun onDestroy() {
        App.clearDetailComponent()
        super.onDestroy()
    }
}