package com.example.seedproject.repository.api.qiita

import kotlinx.coroutines.Deferred

interface QiitaRepository {
  fun getItems(): Deferred<List<String>>
}