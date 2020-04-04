package com.example.seedproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.seedproject.repository.api.qiita.QiitaApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

  // The internal MutableLiveData String that stores the most recent response
  private val _response = MutableLiveData<String>()

  // The external immutable LiveData for the response String
  val response: LiveData<String>
    get() = _response

  // Create a Coroutine scope using a job to be able to cancel when needed
  private var viewModelJob = Job()

  // the Coroutine runs using the Main (UI) dispatcher
  private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    getQiitaItems()
  }

  private fun getQiitaItems() {
    coroutineScope.launch {
      // Get the Deferred object for our Retrofit request
      var getPropertiesDeferred = QiitaApi.retrofitService.getItems()
      try {
        // Await the completion of our Retrofit request
        var listResult = getPropertiesDeferred.await()
        _response.value = "Success: ${listResult} "
      } catch (e: Exception) {
        _response.value = "Failure: ${e.message}"
      }
      Log.d("response", _response.value)
    }
  }
}
