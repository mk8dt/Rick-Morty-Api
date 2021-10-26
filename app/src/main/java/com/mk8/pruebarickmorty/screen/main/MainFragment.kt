package com.mk8.pruebarickmorty.screen.main

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mk8.data.ScreenState
import com.mk8.pruebarickmorty.config.extension.initVerticalRecycler
import com.mk8.pruebarickmorty.databinding.MainLayoutBinding
import com.mk8.pruebarickmorty.screen.adapter.CharactersAdapter
import com.mk8.pruebarickmorty.screen.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment<MainLayoutBinding>() {

    private val mainViewModel by viewModel<MainViewModel>()
    private lateinit var characterAdapter: CharactersAdapter

    override fun initializeBinding(): MainLayoutBinding {
        binding = MainLayoutBinding.inflate(layoutInflater)
        return binding
    }

    override fun initView() {
        mainViewModel.init()
        characterAdapter = CharactersAdapter(layoutInflater) { routeToCharacterDetail(it) }
        with(binding.rvCharacters) {

            initVerticalRecycler(characterAdapter)

            val layoutManager = layoutManager as LinearLayoutManager
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    mainViewModel.notifyLastItemVisible(layoutManager.findLastVisibleItemPosition(), characterAdapter.itemCount)
                }
            })
        }
        mainViewModel.charactersMutableData.observe(viewLifecycleOwner) { manageScreenState(it) }
        mainViewModel.moreCharactersMutableData.observe(viewLifecycleOwner) { manageLoadMoreScreen(it) }
    }

    private fun manageLoadMoreScreen(screenState: CHARACTER_STATE) {
        when (screenState) {
            ScreenState.Loading -> showProgressBar()
            is ScreenState.Success -> {
                hideProgressBar()
                characterAdapter.addMoreCharacter(screenState.data)
            }
            is ScreenState.Error -> {
                hideProgressBar()
                showToast(screenState.errorMessage)
            }
        }
    }

    private fun manageScreenState(screenState: CHARACTER_STATE) {
        when (screenState) {
            ScreenState.Loading -> showProgressBar()
            is ScreenState.Success -> {
                hideProgressBar()
                characterAdapter.updateList(screenState.data)
            }
            is ScreenState.Error -> {
                hideProgressBar()
                showToast(screenState.errorMessage)
            }
        }
    }

    private fun routeToCharacterDetail(identifier: Int) {
        navController.navigate(MainFragmentDirections.routeDetail(identifier))
    }
}