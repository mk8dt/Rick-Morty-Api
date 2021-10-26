package com.mk8.pruebarickmorty.screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mk8.data.repsonse.CharactersResponse
import com.mk8.pruebarickmorty.config.extension.loadImage
import com.mk8.pruebarickmorty.databinding.CharacterItemViewBinding
import com.mk8.pruebarickmorty.screen.base.BaseRecyclerView
import com.mk8.pruebarickmorty.screen.base.BaseViewHolder

class CharactersAdapter(
    private val layoutInflater: LayoutInflater,
    private val onClick: (Int) -> Unit
) : BaseRecyclerView<CharactersResponse, CharactersAdapter.Holder>() {

    fun addMoreCharacter(list: List<CharactersResponse>) {
        itemList += list
        notifyItemRangeChanged(0, itemList.size)
    }

    override fun getViewHolder(parent: ViewGroup, viewType: Int): Holder =
        Holder(CharacterItemViewBinding.inflate(layoutInflater, parent, false))

    inner class Holder(private val binding: CharacterItemViewBinding) : BaseViewHolder<CharactersResponse>(binding) {

        override fun bindData(item: CharactersResponse) {

            with(binding) {

                ivCharacter.loadImage(item.image)
                tvNameCharacter.text = item.name

                root.setOnClickListener { onClick.invoke(item.id) }
            }
        }
    }
}