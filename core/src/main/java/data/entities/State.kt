package data.entities

import data.repositories.Resource

/**
 * Created by Aiden ( hai Le Thanh ) on 27/05/2022.
 * aiden9xx@gmail.com
 */
/**
 * State Management
 */
sealed class State<T> {
    class Loading<T> : State<T>()

    data class Success<T>(val data: T) : State<T>()

    data class Error<T>(val message: String) : State<T>()

    fun isLoading(): Boolean = this is Loading

    fun isSuccessful(): Boolean = this is Success

    fun isFailed(): Boolean = this is Error

    companion object {

        /**
         * Returns [State.Loading] instance.
         */
        fun <T> loading() = Loading<T>()

        /**
         * Returns [State.Success] instance.
         * @param data Data to emit with status.
         */
        private fun <T> success(data: T) =
            Success(data)

        /**
         * Returns [State.Error] instance.
         * @param message Description of failure.
         */
        private fun <T> error(message: String) =
            Error<T>(message)

        /**
         * Returns [State] from [Resource]
         */
        fun <T> fromResource(resource: Resource<T>): State<T> = when (resource) {
            is Resource.Success -> success(resource.data)
            is Resource.Failed -> error(resource.message)
        }
    }
}
