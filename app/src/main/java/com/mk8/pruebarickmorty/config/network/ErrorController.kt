package com.mk8.pruebarickmorty.config.network

object ErrorController {

    const val networkDown = "No tienes conexion a internet"

    const val timeOut = "Se ha execedido el tiempo de respuesta"

    private const val forbidden = "No tienes acceso"

    private const val unAuthorized = "No estas autorizado"

    const val errorGeneral = "Se ha producido un error vuelva a intentarlo"

    const val notFound = "No se ha encontrado"

    private const val updateApp = "Tienes una version antigua, ve al store para actualizar"

    private const val wrongLogin = "EL usuario o contraseña no son correctos"

    private const val methodNotAllowed = "Este método no esta permitido"


    fun checkError(errorCode: Int): String {
        return when (errorCode) {
            303 -> wrongLogin
            401 -> unAuthorized
            403 -> forbidden
            404 -> notFound
            405 -> methodNotAllowed
            in 500..503 -> networkDown
            800 -> updateApp
            else -> errorGeneral
        }
    }
}