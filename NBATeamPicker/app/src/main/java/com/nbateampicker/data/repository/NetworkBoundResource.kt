package com.nbateampicker.data.repository

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.nbateampicker.data.api.ApiErrorResponse
import com.nbateampicker.data.api.ApiResponse
import com.nbateampicker.data.api.ApiSuccessResponse
import com.nbateampicker.data.entity.Resource

/**
 * A generic class that can provide a resource backed by both the sqlite database and the network.
 *
 *
 * You can read more about it in the [Architecture
 * Guide](https://developer.android.com/arch).
 * @param <ResultType>
 * @param <RequestType>
</RequestType></ResultType> */
abstract class NetworkBoundResource<RequestType> {

    private val result = MediatorLiveData<Resource<RequestType>>()

    init {
        setValue(Resource.loading(null))
        fetchFromNetwork()
    }

    @MainThread
    private fun setValue(newValue: Resource<RequestType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

    private fun fetchFromNetwork() {

        val apiResponse = createCall()
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)

            when (response) {
                is ApiSuccessResponse -> {
                    setValue(Resource.success(processResponse(response)))
                }

                is ApiErrorResponse -> {
                    onFetchFailed()
                    setValue(Resource.error(response.error, null))
                }
            }
        }
    }

    protected fun onFetchFailed() { // Handled it error response
    }

    fun asLiveData() = result as LiveData<Resource<RequestType>>

    @WorkerThread
    protected open fun processResponse(response: ApiSuccessResponse<RequestType>) = response.body

    @MainThread
    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>
}