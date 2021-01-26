package com.example.test_app.network

import com.example.test_app.entity.QueryResult
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

object ApiClientProxy {
    fun getQueryProducts(q: String? = ""): Observable<QueryResult> =
        ApiClient.getServiceClient().getQueryProducts("", q!!)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
}