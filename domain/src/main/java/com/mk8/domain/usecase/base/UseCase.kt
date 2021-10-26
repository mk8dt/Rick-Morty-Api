package com.mk8.domain.usecase.base

interface UseCase<T> {

    suspend fun execute(): T
}