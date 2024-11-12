package com.example.lab8_api.ui
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab8_api.data.Photo
import com.example.lab8_api.network.RetrofitInstance
import kotlinx.coroutines.launch

class PhotoViewModel : ViewModel() {
    private val _photos = MutableLiveData<List<Photo>>()
    val photos: LiveData<List<Photo>> get() = _photos

    init {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getPhotos("YOUR_UNSPLASH_ACCESS_KEY")
                _photos.value = response
            } catch (e: Exception) {
                Log.e("PhotoViewModel", "Error fetching photos", e)
            }
        }
    }
}