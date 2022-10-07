package com.example.networkpractice

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class DataSource {

//    private val personLiveData = null;
    private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8888/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
    private val networkApis: NetworkAPIs = retrofit.create()
    private val personLiveData = MutableLiveData(networkApis.fetchPersonList())

//    fun addFlower(flower: Flower) {
//        val currentList = flowersLiveData.value
//        if (currentList == null) {
//            flowersLiveData.postValue(listOf(flower))
//        } else {
//            val updatedList = currentList.toMutableList()
//            updatedList.add(0, flower)
//            flowersLiveData.postValue(updatedList)
//        }
//    }

    /* Removes flower from liveData and posts value. */
//    fun removeFlower(flower: Flower) {
//        val currentList = flowersLiveData.value
//        if (currentList != null) {
//            val updatedList = currentList.toMutableList()
//            updatedList.remove(flower)
//            flowersLiveData.postValue(updatedList)
//        }
//    }

    /* Returns flower given an ID. */
    fun getFlowerForId(id: Long): Person? {
        personLiveData.value?.let { person ->
            return person.firstOrNull{ it.id == id}
        }
        return null
    }

    fun getFlowerList(): LiveData<List<Person>> {
        return personLiveData
    }

    /* Returns a random flower asset for flowers that are added. */
    fun getRandomFlowerImageAsset(): Int? {
        val randomNumber = (initialFlowerList.indices).random()
        return initialFlowerList[randomNumber].image
    }

    companion object {
        private var INSTANCE: DataSource? = null

        fun getDataSource(resources: Resources): DataSource {
            return synchronized(DataSource::class) {
                val newInstance = INSTANCE ?: DataSource(resources)
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}