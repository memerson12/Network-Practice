package com.example.networkpractice

import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkAPIs {
    @GET("/people")
    suspend fun fetchPersonList(): PersonList

    @GET("/people/{id}")
    suspend fun fetchPerson(@Path("id") id: Int): Person
}