package ru.taptm.marvelcomicssample.reposetory.network.exceptions

import java.lang.RuntimeException

class NoNetworkException: RuntimeException {
    constructor() : super()

    constructor(message: String) : super(message)

    constructor(message: String, cause: Throwable) : super(message, cause)
}