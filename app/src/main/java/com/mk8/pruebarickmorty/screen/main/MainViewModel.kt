package com.mk8.pruebarickmorty.screen.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mk8.data.ScreenState
import com.mk8.data.fold
import com.mk8.data.repsonse.CharactersResponse
import com.mk8.domain.usecase.character.GetCharacterListUseCase
import com.mk8.domain.usecase.character.GetMoreCharacterUseCase
import com.mk8.pruebarickmorty.screen.base.BaseViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

typealias CHARACTER_STATE = ScreenState<List<CharactersResponse>, String>

class MainViewModel(
    private val getCharacterListUseCase: GetCharacterListUseCase,
    private val getMoreCharacterUseCase: GetMoreCharacterUseCase
) : BaseViewModel() {

    private val pageThreshold = 10

    private val _charactersMutableData = MutableLiveData<CHARACTER_STATE>()
    val charactersMutableData: LiveData<CHARACTER_STATE>
        get() = _charactersMutableData

    private val _moreCharactersMutableData = MutableLiveData<CHARACTER_STATE>()
    val moreCharactersMutableData: LiveData<CHARACTER_STATE>
        get() = _moreCharactersMutableData

    override fun onCreate() {
        super.onCreate()
        getCharactersList()
    }

    private fun getCharactersList() {
        updateUI(ScreenState.Loading)

        uiScope.launch {
            val result = uiScope.async { withContext(ioContext) { getCharacterListUseCase.execute() } }
            result.await().fold(
                { updateUI(ScreenState.Error(it)) },
                { updateUI(ScreenState.Success(it)) }
            )
        }
    }

    private fun getMoreCharacters() {
        uiScope.launch {
            val result = uiScope.async { withContext(ioContext) { getMoreCharacterUseCase.execute() } }
            result.await().fold(
                { updateMoreUI(ScreenState.Error(it)) },
                { updateMoreUI(ScreenState.Success(it)) }
            )
        }
    }

    fun notifyLastItemVisible(lastVisible: Int, sizeList: Int) {
        if (lastVisible >= sizeList / pageThreshold) getMoreCharacters()
    }

    private fun updateUI(state: CHARACTER_STATE) {
        _charactersMutableData.postValue(state)
    }

    private fun updateMoreUI(state: CHARACTER_STATE) {
        _moreCharactersMutableData.postValue(state)
    }
}