package data.repositories

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import retrofit2.Response

/**
 * Created by Aiden ( hai Le Thanh ) on 27/05/2022.
 * aiden9xx@gmail.com
 */
/**
 * [RESULT] Type for database.
 * [REQUEST] Type for network.
 */
@ExperimentalCoroutinesApi
abstract class FetchDataFlow<RESULT, REQUEST> {

    fun asFlow() = flow<Resource<RESULT>> {

        emit(Resource.Success(fetchNewsFromLocalDatabase().first()))

        val apiResponse = fetchNewsFromServer()
        val remoteArticle = apiResponse.body()

        if (apiResponse.isSuccessful && remoteArticle != null) {
            storeNewsToLocalDatabase(remoteArticle)
        } else {
            emit(Resource.Failed(apiResponse.message()))
        }

        emitAll(
            fetchNewsFromLocalDatabase().map {
                Resource.Success<RESULT>(it)
            }
        )
    }.catch { e ->
        e.printStackTrace()
        emit(Resource.Failed("Network error"))
    }

    /**
     * Store news to the local database
     */
    @WorkerThread
    protected abstract suspend fun storeNewsToLocalDatabase(response: REQUEST)

    /**
     * Fetch news from local database
     */
    @MainThread
    protected abstract fun fetchNewsFromLocalDatabase(): Flow<RESULT>

    /**
     * Fetch news from the server
     */
    @MainThread
    protected abstract suspend fun fetchNewsFromServer(): Response<REQUEST>
}
