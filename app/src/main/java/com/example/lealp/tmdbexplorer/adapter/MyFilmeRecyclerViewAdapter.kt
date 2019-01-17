package com.example.lealp.tmdbexplorer.adapter

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.lealp.tmdbexplorer.IMAGE_URL
import com.example.lealp.tmdbexplorer.R


import com.example.lealp.tmdbexplorer.fragment.FilmeFragment.OnListFragmentInteractionListener
import com.example.lealp.tmdbexplorer.dummy.DummyContent.DummyItem
import com.example.lealp.tmdbexplorer.model.Filme

import kotlinx.android.synthetic.main.fragment_filme.view.*
import org.apache.commons.lang3.StringUtils

import java.util.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyFilmeRecyclerViewAdapter(
    private val mValues: List<Filme>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<MyFilmeRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Filme
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_filme, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mTitleView.text = item.title
        val year = StringUtils.substringBefore(item.release_date,"-")


        holder.mYearView.text = year.toString()

        val actualYear = Calendar.getInstance().get(Calendar.YEAR)
        holder.mYearView.setTextColor(ContextCompat.getColor(holder.mView.context,
            if(actualYear == year.toIntOrNull()) android.R.color.holo_red_dark else R.color.textColorSecundary
        ))


        Glide.with(holder.mView).load( "$IMAGE_URL/w500${item.poster_path}").apply(RequestOptions.placeholderOf(
            R.drawable.ic_placeholder
        )).into(holder.mPosterView)

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mTitleView: TextView = mView.textViewTitle
        val mYearView: TextView = mView.year
        val mPosterView: ImageView = mView.poster

        override fun toString(): String {
            return super.toString() + " '" + mYearView.text + "'"
        }
    }
}
