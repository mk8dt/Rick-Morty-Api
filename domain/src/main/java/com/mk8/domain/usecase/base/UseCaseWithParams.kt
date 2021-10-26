package com.mk8.domain.usecase.base

interface UseCaseWithParams<out T, in P> {

    suspend fun execute(params: P): T
}