package com.example.retrofitdemo.retrofit

import com.example.retrofitdemo.data.Albums
import com.example.retrofitdemo.data.AlbumsItem
import retrofit2.Response
import retrofit2.http.*

interface AlbumsService {
    //    https://jsonplaceholder.typicode.com/albums
    @GET("/albums")
    suspend fun getAlbums(): Response<Albums>

    //    https://jsonplaceholder.typicode.com/albums?userId=3
    @GET("/albums")
    suspend fun getSortedAlbums(@Query("userId") userId: Int): Response<Albums>

    //    https://jsonplaceholder.typicode.com/albums/3
    @GET("/albums/{id}")
    suspend fun getAlbum(@Path(value = "id") id: Int): Response<AlbumsItem>

    @POST("/albums")
    suspend fun uploadAlbum(@Body album:AlbumsItem): Response<AlbumsItem>
}