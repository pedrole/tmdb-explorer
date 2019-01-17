package com.example.lealp.tmdbexplorer.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.lealp.tmdbexplorer.model.Filme
import com.example.lealp.tmdbexplorer.R
import com.github.kittinunf.fuel.Fuel

import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*
import java.text.DecimalFormat
import java.util.*

const val MOVIE_ID = "movie_id"
class DetailActivity : AppCompatActivity() {
    private  val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w780"
    private val FORMATO_REVENUE = DecimalFormat.getInstance(Locale.US).apply { minimumFractionDigits = 2 }
    private val FORMATO_RUNTIME = DecimalFormat("0 min")




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)

        supportActionBar?.let{ it.setDisplayHomeAsUpEnabled(true);  it.setDisplayShowTitleEnabled(false)}

        val movieId = intent.getLongExtra(MOVIE_ID,0)






        progress?.visibility = View.VISIBLE
        Fuel.get("movie/$movieId")
            .responseObject(Filme.DetailDeserializer()) { request, response, result ->
                val (filme, err) = result

                filme?.let {

                    val s = "$IMAGE_BASE_URL${it.backdrop_path}"
                    Glide.with(this).load(s).apply(RequestOptions.placeholderOf(R.color.colorPrimary)).into(backdrop)
                    textViewTitle.text = it.title
                    revenue.text = FORMATO_REVENUE.format(it.revenue)
                    runtime.text = FORMATO_RUNTIME.format(it.runtime)
                    overview.text = it.overview


                }

                progress.visibility= View.GONE
               err?.let { finish() }

               // activity?.progress?.visibility = View.GONE

            }
    }

}
