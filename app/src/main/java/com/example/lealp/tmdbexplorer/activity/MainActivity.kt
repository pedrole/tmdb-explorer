package com.example.lealp.tmdbexplorer.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import com.example.lealp.tmdbexplorer.model.Filme
import com.example.lealp.tmdbexplorer.fragment.FilmeFragment
import com.example.lealp.tmdbexplorer.R


import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(),
    FilmeFragment.OnListFragmentInteractionListener, SearchView.OnQueryTextListener {
    override fun onQueryTextSubmit(p0: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(p0: String?): Boolean {

        var fragment = supportFragmentManager.findFragmentById(R.id.movieListFragment) as FilmeFragment

        fragment.updateList(p0);


        return false;
    }

    override fun onListFragmentInteraction(item: Filme?) {
       startActivity(Intent(this, DetailActivity::class.java).apply { putExtra(
           MOVIE_ID,item?.id)})
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        search.setOnQueryTextListener(this)


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
