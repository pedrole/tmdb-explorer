package com.example.lealp.tmdbexplorer.model

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import org.json.JSONObject

data class Filme(
    val id: Long,
    val title: String,
    val release_date: String,
    val poster_path: String,
    val backdrop_path: String,
    val overview: String,
    val revenue: Int,
    val runtime: Int
) {


    class Deserializer : ResponseDeserializable<Array<Filme>> {
        override fun deserialize(content: String): Array<Filme>?{

            return  Gson().fromJson(   JSONObject(content).getJSONArray("results").toString(), Array<Filme>::class.java)
        }
    }
    class DetailDeserializer : ResponseDeserializable<Filme> {
        override fun deserialize(content: String): Filme? {
            return Gson().fromJson(content, Filme::class.java)
        }
    }




}