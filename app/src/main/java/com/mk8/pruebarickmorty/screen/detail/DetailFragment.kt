package com.mk8.pruebarickmorty.screen.detail

import com.mk8.data.ScreenState
import com.mk8.data.repsonse.CharactersResponse
import com.mk8.pruebarickmorty.R
import com.mk8.pruebarickmorty.config.extension.lazyUnSynchronized
import com.mk8.pruebarickmorty.config.extension.loadImage
import com.mk8.pruebarickmorty.databinding.DetailLayoutBinding
import com.mk8.pruebarickmorty.screen.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment<DetailLayoutBinding>() {

    private val detailViewModel by viewModel<DetailViewModel>()
    private val identifier by lazyUnSynchronized {
        arguments?.let { DetailFragmentArgs.fromBundle(it).identifier }
    }

    override fun initializeBinding(): DetailLayoutBinding {
        binding = DetailLayoutBinding.inflate(layoutInflater)
        return binding
    }

    override fun initView() {
        detailViewModel.init()
        identifier?.let { detailViewModel.getLastLocationByIdentifier(it) }
        detailViewModel.characterDetailMutableData.observe(viewLifecycleOwner) { manageScreenState(it) }
    }

    private fun manageScreenState(screenState: CHARACTER_DETAIL_STATE) {
        when (screenState) {
            ScreenState.Loading -> showProgressBar()
            is ScreenState.Success -> {
                hideProgressBar()
                bindCharacterDetail(screenState.data)
            }
            is ScreenState.Error -> {
                hideProgressBar()
                showToast(screenState.errorMessage)
            }
        }
    }

    private fun bindCharacterDetail(character: CharactersResponse) {

        with(binding) {

            ivCharacter.loadImage(character.image)
            tvCharacterName.text = character.name
            tvSpecieCharacter.text = character.species
            tvLocationCharacter.text = character.location.name
            tvStatusCharacter.text = character.status

            ivFavourite.setImageResource(
                if (character.isFavourite) R.drawable.ic_favorite_fill else R.drawable.ic_favorite_empty
            )

            ivFavourite.setOnClickListener {
                if (character.isFavourite) {
                    character.isFavourite = false
                    detailViewModel.removeFavouriteCharacter(character)
                    ivFavourite.setImageResource(R.drawable.ic_favorite_empty)
                } else {
                    character.isFavourite = true
                    detailViewModel.addFavouriteCharacter(character)
                    ivFavourite.setImageResource(R.drawable.ic_favorite_fill)
                }
            }
        }
    }
}