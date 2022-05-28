package com.aiden.newsdemo.presentation.articles

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import data.entities.Article
import data.entities.State
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import domain.usecases.GetArticlesUseCase
import domain.usecases.RemoveArticlesUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.collect

/**
 * ViewModel for [ArticlesFragment].
 */
@ExperimentalCoroutinesApi
@HiltViewModel
class ArticlesViewModel @Inject constructor(private val removeArticlesUseCase: RemoveArticlesUseCase, private val getArticlesUseCase: GetArticlesUseCase) :
    ViewModel() {
    private val _articlesLiveData = MutableLiveData<State<List<Article>>>()
    val articlesLiveData: LiveData<State<List<Article>>> = _articlesLiveData

    fun getArticles() {
        viewModelScope.launch {
            removeArticlesUseCase.invoke()
                .onStart {}
                .map {}
                .collect {}
        }

        viewModelScope.launch {
            getArticlesUseCase.invoke()
                .onStart { _articlesLiveData.value = State.loading() }
                .map { resource -> State.fromResource(resource) }
                .collect { state -> _articlesLiveData.value = state }
        }
    }
}