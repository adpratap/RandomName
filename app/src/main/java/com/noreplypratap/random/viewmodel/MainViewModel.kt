package com.noreplypratap.random.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noreplypratap.random.model.RandomUser
import com.noreplypratap.random.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _responseLiveData = MutableLiveData<RandomUser>()

    val responseLiveData : LiveData<RandomUser>
    get() = _responseLiveData

    fun getRandomNames() = viewModelScope.launch {
        repository.getUserData().let { results ->
            if (results.isSuccessful){
                _responseLiveData.postValue(results.body())
            }else{
                Log.d("ADP",results.message().toString())
            }
        }
    }
}