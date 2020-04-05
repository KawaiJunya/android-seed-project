package com.example.seedproject.repository.qiita

import com.example.seedproject.data.model.qiita.QiitaArticle
import kotlinx.coroutines.Deferred

interface QiitaRepository {
  fun getItems(): Deferred<List<QiitaArticle>>
}