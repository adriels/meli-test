package com.example.test_app.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class QueryResult(
        var site_id: String?,
        var query: String?,
        var paging: Paging?,
        var results: List<Result>?
): Parcelable

@Parcelize
data class Paging(
        var total: Int?,
        var offset: Int?,
        var limit: Int?,
        var primary_results: Int?
): Parcelable

@Parcelize
data class Result(
        var id: String?,
        var site_id: String?,
        var title: String?,
        var seller: Seller?,
        var price: Float?,
        var currency_id: String?,
        var available_quantity: Int?,
        var sold_quantity: Int?,
        var buying_mode: String?,
        var listing_type_id: String?,
        var stop_time: String?,
        var condition: String?,
        var permalink: String?,
        var thumbnail: String?,
        var accepts_mercadopago: Boolean?,
        var installments: Installment?,
        var address: Address?,
        var shipping: Shipping?,
        var seller_address: SellerAddress?,
        var attributes: List<Attributes>?,
        var original_price: Float?,
        var category_id: String?,
        var official_store_id: Int?,
        var catalog_product_id: String?,
        var tags: List<String>?,
        var catalog_listing: Boolean?
): Parcelable

@Parcelize
data class Seller(
        var id: String?,
        var power_seller_status: String?,
        var car_dealer: Boolean?,
        var real_estate_agency: Boolean?,
        var tags: List<String>?
): Parcelable

@Parcelize
data class Installment(
        var quantity: Int?,
        var amount: Float?,
        var rate: Float?,
        var currency_id: String?
): Parcelable

@Parcelize
data class Address(
        var state_id: String?,
        var state_name: String?,
        var city_id: String?,
        var city_name: String?
): Parcelable

@Parcelize
data class Shipping(
        var free_shipping: Boolean?,
        var mode: String?,
        var tags: List<String>?,
        var logistic_type: String?,
        var store_pick_up: Boolean?
): Parcelable

@Parcelize
data class SellerAddress(
        var id: String?,
        var comment: String?,
        var address_line: String?,
        var zip_code: String?,
        var country: CommonClass?,
        var state: CommonClass?,
        var city: CommonClass?,
        var latitude: String?,
        var longitude: String?
): Parcelable

@Parcelize
data class Attributes(
        var name: String?,
        var value_id: String?,
        var value_name: String?,
        var attribute_group_id: String?,
        var attribute_group_name: String?,
        var source: Long?,
        var id: String?,
): Parcelable

@Parcelize
data class CommonClass(
        var id: String?,
        var name: String?
): Parcelable