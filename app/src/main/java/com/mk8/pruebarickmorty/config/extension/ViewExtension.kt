package com.mk8.pruebarickmorty.config.extension

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mk8.pruebarickmorty.screen.base.BaseRecyclerView

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun RecyclerView.initVerticalRecycler(adapter: BaseRecyclerView<*, *>, isGridLayout: Boolean = false) = run {
    layoutManager = if (isGridLayout) GridLayoutManager(context, 2) else LinearLayoutManager(context)
    setHasFixedSize(true)
    this.adapter = adapter
}

fun RecyclerView.initHorizontalRecycler(adapter: BaseRecyclerView<*, *>, block: (RecyclerView) -> Unit = {}) = run {
    layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    setHasFixedSize(true)
    this.adapter = adapter
    block.invoke(this)
}


fun EditText.getStringText() = text.toString()

fun EditText.isNotEmpty() = getStringText().isNotEmpty()

fun EditText.onChange(text: (String) -> Unit) {

    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            text(s.toString())
        }
    })
}

fun ImageView.loadImage(url: String?) {
    load(url) {
        target {
            setImageDrawable(it)
        }
    }
}