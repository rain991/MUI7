package com.example.mui7.data

data class TaxPayer(
    val owner: Credentials,
    val address : String,
    val instanceName : String
) {
    data class Credentials(val name: String, val surname: String)
}