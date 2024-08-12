package com.example.retrofit


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MainViewModel : ViewModel() {

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> = _posts

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        viewModelScope.launch {
            try {
                val postList = RetrofitInstance.api.getPosts()
                _posts.value = postList
                Log.d("MainViewModel", "Posts successfully fetched")
            } catch (e: IOException) {
                Log.e("MainViewModel", "Network error", e)
            } catch (e: HttpException) {
                Log.e("MainViewModel", "HTTP error", e)
            } catch (e: Exception) {
                Log.e("MainViewModel", "Unexpected error", e)
            }
        }
    }
}


