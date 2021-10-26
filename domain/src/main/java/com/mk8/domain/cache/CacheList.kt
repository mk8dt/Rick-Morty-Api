package com.mk8.domain.cache

abstract class CacheList<T>(private var validityTime: Long = VALIDITY_LONG_TIME) {

    companion object {
        const val VALIDITY_LONG_TIME = 1200000L
    }

    private var data: MutableList<T> = mutableListOf()
    private var timestamp: Long = 0

    private fun isValidTime(): Boolean = System.currentTimeMillis() - timestamp < validityTime

    fun load(): MutableList<T> = if (data.isNotEmpty() && isValidTime()) data else clear()

    private fun save(value: MutableList<T>) {
        data = value
        updateTimestamp()
    }

    fun addElement(element: T) {
        load().add(element)
        updateTimestamp()
    }

    fun removeElement(element: T) {
        load().remove(element)
        updateTimestamp()
    }

    private fun clear(): MutableList<T> {
        data = mutableListOf()
        return data
    }

    private fun updateTimestamp() {
        timestamp = System.currentTimeMillis()
    }
}