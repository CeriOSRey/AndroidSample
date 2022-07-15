package com.example.reysampleandroid.ui

import androidx.lifecycle.*
import com.example.reysampleandroid.model.Joke
import com.example.reysampleandroid.repository.MainRepository
import com.example.reysampleandroid.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val mainRepository: MainRepository,
     private val savedStateHandle: SavedStateHandle
): ViewModel()
{
//    private val _dataState: MutableLiveData<DataState<List<Joke>>> = MutableLiveData()
//    val dataState: LiveData<DataState<List<Joke>>>
//        get () = _dataState

    private val _dataState: MutableLiveData<DataState<Joke>> = MutableLiveData()
    val dataState: LiveData<DataState<Joke>>
        get () = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when(mainStateEvent) {
                is MainStateEvent.GetJokeEvents -> {
                    mainRepository.getJoke()
                        .onEach { dataState ->
                            _dataState.value = dataState
                        }
                        .launchIn(viewModelScope)
                }
                is MainStateEvent.None -> {
                    // nothing going on here
                }
            }
        }
    }
}

sealed class MainStateEvent {
    object GetJokeEvents: MainStateEvent()
    object None: MainStateEvent()
}