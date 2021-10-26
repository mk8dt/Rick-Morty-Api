package com.mk8.pruebarickmorty.config.extension

fun <T> lazyUnSynchronized(initializer: () -> T): Lazy<T> = lazy(LazyThreadSafetyMode.NONE, initializer)