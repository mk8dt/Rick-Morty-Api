package com.mk8.pruebarickmorty.screen.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mk8.data.ScreenState
import com.mk8.data.fold
import com.mk8.data.repsonse.CharactersResponse
import com.mk8.domain.usecase.character.AddFavouriteCharacterUseCase
import com.mk8.domain.usecase.character.CheckIfCharacterIsFavouriteUseCase
import com.mk8.domain.usecase.character.GetCharacterByIdentifierUseCase
import com.mk8.domain.usecase.character.RemoveFavouriteCharacterUseCase
import com.mk8.pruebarickmorty.screen.base.BaseViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

typealias CHARACTER_DETAIL_STATE = ScreenState<CharactersResponse, String>

class DetailViewModel(
    private val getCharacterByIdentifierUseCase: GetCharacterByIdentifierUseCase,
    private val addFavouriteCharacterUseCase: AddFavouriteCharacterUseCase,
    private val removeFavouriteCharacterUseCase: RemoveFavouriteCharacterUseCase,
    private val checkIfCharacterIsFavouriteUseCase: CheckIfCharacterIsFavouriteUseCase
) : BaseViewModel() {

    private val _characterDetailMutableData = MutableLiveData<CHARACTER_DETAIL_STATE>()
    val characterDetailMutableData: LiveData<CHARACTER_DETAIL_STATE>
        get() = _characterDetailMutableData

    fun getLastLocationByIdentifier(identifier: Int) {
        updateUI(ScreenState.Loading)

        uiScope.launch {
            val result = uiScope.async { withContext(ioContext) { getCharacterByIdentifierUseCase.execute(identifier) } }

            result.await().fold(
                { updateUI(ScreenState.Error(it)) },
                { checkIfCharacterIsFavourite(it) }
            )
        }
    }

    private fun checkIfCharacterIsFavourite(charactersResponse: CharactersResponse) {
        viewModelScope.launch {
            val result = checkIfCharacterIsFavouriteUseCase.execute(charactersResponse)

            result.fold(
                { ScreenState.Error(it) },
                { charactersResponse.isFavourite = it
                    updateUI(ScreenState.Success(charactersResponse))
                }
            )
        }
    }

    fun addFavouriteCharacter(charactersResponse: CharactersResponse) {
        viewModelScope.launch { addFavouriteCharacterUseCase.execute(charactersResponse) }
    }

    fun removeFavouriteCharacter(charactersResponse: CharactersResponse) {
        viewModelScope.launch { removeFavouriteCharacterUseCase.execute(charactersResponse) }
    }

    private fun updateUI(state: CHARACTER_DETAIL_STATE) {
        _characterDetailMutableData.postValue(state)
    }
}