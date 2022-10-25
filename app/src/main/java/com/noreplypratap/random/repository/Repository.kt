package com.noreplypratap.random.repository

import com.noreplypratap.random.api.APIService
import com.noreplypratap.random.model.RandomUser
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: APIService )  {

    suspend fun getUserData() : Response<RandomUser>{
        return apiService.getUsers()
    }
}
