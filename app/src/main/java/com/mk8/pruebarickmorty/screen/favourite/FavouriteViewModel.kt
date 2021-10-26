package com.mk8.pruebarickmorty.screen.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mk8.data.ScreenState
import com.mk8.data.fold
import com.mk8.domain.usecase.character.GetFavouritesUseCase
import com.mk8.pruebarickmorty.screen.base.BaseViewModel
import com.mk8.pruebarickmorty.screen.main.CHARACTER_STATE
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavouriteViewModel(
    private val getFavouritesUseCase: GetFavouritesUseCase
) : BaseViewModel() {

    private val _charactersMutableData = MutableLiveData<CHARACTER_STATE>()
    val charactersMutableData: LiveData<CHARACTER_STATE>
        get() = _charactersMutableData

    override fun onCreate() {
        super.onCreate()
        getFavouriteCharacters()
    }

    private fun getFavouriteCharacters() {
        updateUI(ScreenState.Loading)
        uiScope.launch {
            val result = uiScope.async { withContext(ioContext) { getFavouritesUseCase.execute() } }

            result.await().fold(
                { updateUI(ScreenState.Error(it)) },
                { updateUI(ScreenState.Success(it)) }
            )
        }
    }

    private fun updateUI(state: CHARACTER_STATE) {
        _charactersMutableData.postValue(state)
    }
}