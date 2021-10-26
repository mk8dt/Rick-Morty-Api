package com.mk8.pruebarickmorty.screen

import com.mk8.pruebarickmorty.R
import com.mk8.pruebarickmorty.databinding.BaseLayoutBinding
import com.mk8.pruebarickmorty.screen.base.BaseActivity
import com.mk8.pruebarickmorty.screen.main.MainFragmentDirections

class MainActivity : BaseActivity<BaseLayoutBinding>() {

    override fun initializeBinding(): BaseLayoutBinding {
        binding = BaseLayoutBinding.inflate(layoutInflater)
        return binding
    }

    override fun initView() {
        with(binding.toolbar) {
            title = "Rick and Morty"
            inflateMenu(R.menu.toolbar_menu)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.start -> routeToFavourites()
                }
                true
            }
        }
    }

    fun progressBar() = binding.progressBar

    private fun routeToFavourites() {
        navController.navigate(MainFragmentDirections.routeFavourite())
    }
}