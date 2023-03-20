package io.github.kmehasan.mvvmandretrofit2template.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.kmehasan.mvvmandretrofit2template.repository.Repository
import io.github.kmehasan.mvvmandretrofit2template.response.charecter_model.CharecterResponse
import kotlinx.coroutines.*

class MainScreenViewModel : ViewModel() {
    var repository = Repository()

    //for coroutine Exception
    val errorMessage = MutableLiveData<String>()
    val isLoading = MutableLiveData(false)
    var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Poor Connection")
        Log.e("TAG", "Error : ${throwable.localizedMessage}")
    }
    private fun onError(message: String) {
        errorMessage.postValue(message)
        Log.e("TAG", "onError: $message")
    }

    fun getCharacterList(): MutableLiveData<CharecterResponse> {
        isLoading.postValue(true)
        val characterResponse: MutableLiveData<CharecterResponse> = MutableLiveData()
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = repository.getCharacterList()
            withContext(Dispatchers.Main) {
                isLoading.postValue(false)
                if (response.isSuccessful) {
                    characterResponse.postValue(response.body())
                } else {
                    onError("Error :: get Product :: ${response.message()}")
                }
            }
        }
        return characterResponse
    }
}