package com.example.seedproject.ui.qiita

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import com.example.seedproject.R

class QiitaArticleListFragment : Fragment() {

  private val viewModel: QiitaArticleListViewModel = ViewModelProvider(this).get(QiitaArticleListViewModel::class.java)

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.qiita_article_list_fragment, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel.getQiitaItems()
  }

  companion object {
    fun newInstance() = QiitaArticleListFragment()
  }
}
