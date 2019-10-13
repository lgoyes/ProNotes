package com.lamadridblandongoyes.pronotes.architecturebasis

interface IErrorProcessor {
    var errorHandler: IErrorHandler?

    fun handleException(error: Throwable) {
        error.printStackTrace()
        error.localizedMessage?.let {
            errorHandler?.showError(it)
        }
    }
}