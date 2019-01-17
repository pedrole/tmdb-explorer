package com.example.lealp.tmdbexplorer.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager
import kotlinx.android.synthetic.main.content_main.*
import kotlin.collections.ArrayList
import android.support.v7.widget.DividerItemDecoration
import com.example.lealp.tmdbexplorer.adapter.MyFilmeRecyclerViewAdapter
import com.example.lealp.tmdbexplorer.R
import com.example.lealp.tmdbexplorer.model.Filme
import com.example.lealp.tmdbexplorer.view.EmptyRecyclerView


/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [FilmeFragment.OnListFragmentInteractionListener] interface.
 */
class FilmeFragment : Fragment() {

    // TODO: Customize parameters
    private var columnCount = 1

    private var listener: OnListFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    private val data: ArrayList<Filme> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_filme_list, container, false)



        with(FuelManager.instance) {
            basePath = "https://api.themoviedb.org/3"; baseParams =
                listOf("api_key" to "83d01f18538cb7a275147492f84c3698")
        }

        // Set the adapter
        if (view is EmptyRecyclerView) {
            with(view) {
                //setEmptyView(activity?.empty_view!!)
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                addItemDecoration(
                    DividerItemDecoration(
                        context!!,
                        DividerItemDecoration.VERTICAL
                    )
                )
                adapter = MyFilmeRecyclerViewAdapter(data, listener)


            }
        }
        return view
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.empty_view?.let { (view as? EmptyRecyclerView)?.setEmptyView(it) }


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    fun updateList(p0: String?) {


        activity?.progress?.visibility = View.VISIBLE
        Fuel.get("search/movie", parameters = listOf("query" to p0))
            .responseObject(Filme.Deserializer()) { request, response, result ->
                val (filme, err) = result

                filme?.let { data.clear(); data.addAll(it); (view as RecyclerView).adapter?.notifyDataSetChanged() }

                activity?.progress?.visibility = View.GONE

            }

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson
     * [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: Filme?)
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            FilmeFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}
