package com.example.seedproject.ui.qiita

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.seedproject.data.model.qiita.QiitaArticle
import com.example.seedproject.repository.qiita.api.QiitaApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class QiitaArticleListViewModel : ViewModel() {

  // The internal MutableLiveData String that stores the most recent response
  private val _response = MutableLiveData<List<QiitaArticle>>()

  // The external immutable LiveData for the response String
  val qiitaArticle: LiveData<List<QiitaArticle>>
    get() = _response

  // Create a Coroutine scope using a job to be able to cancel when needed
  private var viewModelJob = Job()

  // the Coroutine runs using the Main (UI) dispatcher
  private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

  fun getQiitaItems() {
    coroutineScope.launch {
      // Get the Deferred object for our Retrofit request
      var getPropertiesDeferred = QiitaApi.retrofitService.getItems()
      try {
        // Await the completion of our Retrofit request
        var listResult = getPropertiesDeferred.await()
        _response.value = listResult
        listResult.map { it ->
          Log.d("response", it.title)
        }
      } catch (e: Exception) {
        Log.d("response", e.message)
      }
    }
  }

}
