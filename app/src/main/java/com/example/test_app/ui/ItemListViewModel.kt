package com.example.test_app.ui

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test_app.entity.QueryResult
import com.example.test_app.network.ApiClientProxy
import com.example.test_app.network.ApiErrorUtil
import io.reactivex.rxjava3.disposables.CompositeDisposable

class ItemListViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val mutableResponseLiveData = MutableLiveData<QueryResult>()
    val mutableErrorLiveData = MutableLiveData<String>()

    fun getQueryProducts(context: Context, q: String?) {
        compositeDisposable.add(ApiClientProxy.getQueryProducts(q)
            .subscribe(
                {
                    mutableResponseLiveData.postValue(it)
                },
                {
                    mutableErrorLiveData.postValue(ApiErrorUtil.getErrorMessage(it, context))
                }
            )
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
    }
}