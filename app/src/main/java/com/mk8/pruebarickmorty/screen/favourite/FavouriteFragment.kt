package com.mk8.pruebarickmorty.screen.favourite

import com.mk8.data.ScreenState
import com.mk8.pruebarickmorty.config.extension.gone
import com.mk8.pruebarickmorty.config.extension.initVerticalRecycler
import com.mk8.pruebarickmorty.config.extension.visible
import com.mk8.pruebarickmorty.databinding.FavouriteLayoutBinding
import com.mk8.pruebarickmorty.screen.adapter.CharactersAdapter
import com.mk8.pruebarickmorty.screen.base.BaseFragment
import com.mk8.pruebarickmorty.screen.main.CHARACTER_STATE
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouriteFragment : BaseFragment<FavouriteLayoutBinding>() {

    private val favouriteViewModel by viewModel<FavouriteViewModel>()
    private lateinit var characterAdapter: CharactersAdapter

    override fun initializeBinding(): FavouriteLayoutBinding {
        binding = FavouriteLayoutBinding.inflate(layoutInflater)
        return binding
    }

    override fun initView() {
        favouriteViewModel.init()
        characterAdapter = CharactersAdapter(layoutInflater) { routeToCharacterDetail(it) }
        binding.rvCharacters.initVerticalRecycler(characterAdapter)
        favouriteViewModel.charactersMutableData.observe(viewLifecycleOwner) { manageScreenState(it) }
    }

    private fun manageScreenState(screenState: CHARACTER_STATE) {
        when (screenState) {
            ScreenState.Loading -> showProgressBar()
            is ScreenState.Success -> {
                hideProgressBar()
                if (screenState.data.isEmpty()) showEmptyLabel() else characterAdapter.updateList(screenState.data)
            }
            is ScreenState.Error -> {
                hideProgressBar()
                showToast(screenState.errorMessage)
            }
        }
    }

    private fun showEmptyLabel() {
        with(binding) {
            tvEmptyCharacters.visible()
            rvCharacters.gone()
        }
    }

    private fun routeToCharacterDetail(identifier: Int) {
        navController.navigate(FavouriteFragmentDirections.routeDetail(identifier))
    }
}