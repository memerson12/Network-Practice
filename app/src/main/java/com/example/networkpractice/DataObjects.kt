package com.example.networkpractice

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Person(
    val id: Int,
    val name: String,
    val avatar: String
)

@JsonClass(generateAdapter = true)
data class PersonList(
    val people: List<Person>
)