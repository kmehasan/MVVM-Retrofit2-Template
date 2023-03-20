package io.github.kmehasan.mvvmandretrofit2template.repository

import io.github.kmehasan.mvvmandretrofit2template.network.RetrofitBuilder

class Repository {
    var api = RetrofitBuilder.API_SERVICE

    suspend fun getCharacterList() = api.getCharacterList()
}