package com.noreplypratap.random.api

import com.noreplypratap.random.model.RandomUser
import retrofit2.Response
import retrofit2.http.GET

interface APIService {

    @GET("/api/v2/users")
    suspend fun getUsers() : Response<RandomUser>

}