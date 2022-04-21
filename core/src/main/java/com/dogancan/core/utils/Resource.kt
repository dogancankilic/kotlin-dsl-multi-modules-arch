package com.dogancan.core.utils

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {

    /**
     * Enum for Status
     */
    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {

        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: Throwable, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg.message)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }


}

