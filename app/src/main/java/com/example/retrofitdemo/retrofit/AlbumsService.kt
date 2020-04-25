package com.example.retrofitdemo.retrofit

import com.example.retrofitdemo.data.Albums
import retrofit2.Response

interface AlbumsService {

    suspend fun getAlbums(): Response<Albums>
}