package com.d3if0154.listmovie.ui.daftarMovie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d3if0154.listmovie.model.DaftarMovie
import com.d3if0154.listmovie.network.DaftarMovieApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DaftarMovieViewModel: ViewModel() {

    private val data = MutableLiveData<List<DaftarMovie>>()
    private val status = MutableLiveData<DaftarMovieApi.ApiStatus>()

    init {
        retriveData()
    }

    private fun retriveData(){
        viewModelScope.launch (Dispatchers.IO){
            status.postValue(DaftarMovieApi.ApiStatus.LOADING)
            try {
                data.postValue(DaftarMovieApi.service.getDaftarMovie())
                status.postValue(DaftarMovieApi.ApiStatus.SUCCESS)
            }catch (e: Exception){
                Log.d("MainViewModel", "Failure: ${e.message}")
                status.postValue(DaftarMovieApi.ApiStatus.FAILED)
            }
        }
    }

    fun getData(): LiveData<List<DaftarMovie>> = data

    fun getStatus(): LiveData<DaftarMovieApi.ApiStatus> = status
}