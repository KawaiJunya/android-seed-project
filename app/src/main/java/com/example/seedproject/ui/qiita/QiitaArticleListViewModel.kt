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

class QiitaArticleListViewModel internal constructor(

): ViewModel() {

  private val _response = MutableLiveData<List<QiitaArticle>>()
  val qiitaArticle: LiveData<List<QiitaArticle>>
    get() = _response

  private val viewModelJob   = Job()
  private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

  fun getQiitaItems() {
    coroutineScope.launch {
      try {
        var listResult = QiitaApi.retrofitService.getItems().await()
        _response.value = listResult
        listResult.map { it -> Log.d("response", it.title) }
      } catch (e: Exception) {
        Log.d("response", e.message)
      }
    }
  }

}
