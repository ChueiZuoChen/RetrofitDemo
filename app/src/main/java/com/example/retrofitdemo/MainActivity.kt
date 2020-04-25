package com.example.retrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.example.retrofitdemo.data.Albums
import com.example.retrofitdemo.data.AlbumsItem
import com.example.retrofitdemo.retrofit.AlbumsService
import com.example.retrofitdemo.retrofit.RetrofitInstance
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var retrofitService: AlbumsService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        retrofitService = RetrofitInstance
            .getRetrofitInstance()
            .create(AlbumsService::class.java)

//        getRequestWithPathParameters()
//        getRequestWithQueryParameters()
        uploadAlbums()


    }


    private fun getRequestWithQueryParameters() {
        val responseLiveData: LiveData<Response<Albums>> = liveData {
//            val response: Response<Albums> = retrofitService.getAlbums()
            val response = retrofitService.getSortedAlbums(7)
            emit(response)
        }

        responseLiveData.observe(this, Observer {
            val albumsList = it.body()?.listIterator()
            if (albumsList != null) {
                while (albumsList.hasNext()) {
                    val albumsItem = albumsList.next()
//                    Log.d("MYTAG", albumsItem.title)

                    val result = " " + "Albmus title : ${albumsItem.title} \n" +
                            " " + "Albmus id : ${albumsItem.id} \n" +
                            " " + "Albmus userId : ${albumsItem.userId} \n\n"

                    tv_1.append(result)
                }
            }
        })
    }

    private fun getRequestWithPathParameters() {
        //path parameter example
        val pathResponse: LiveData<Response<AlbumsItem>> = liveData {
            val response = retrofitService.getAlbum(3)
            emit(response)
        }

        pathResponse.observe(this, Observer {
            val title: String? = it.body()?.title
            Toast.makeText(applicationContext, title, Toast.LENGTH_LONG).show()
        })
    }

    private fun uploadAlbums(){
        val albums = AlbumsItem(10,"ddd",10)
        val postResponse:LiveData<Response<AlbumsItem>> = liveData {
            val response:Response<AlbumsItem> = retrofitService.uploadAlbum(albums)
            emit(response)
        }
        postResponse.observe(this, Observer {
            val receivedAlbumsItem = it.body()
            val result = " " + "Albmus title : ${receivedAlbumsItem?.title} \n" +
                    " " + "Albmus id : ${receivedAlbumsItem?.id} \n" +
                    " " + "Albmus userId : ${receivedAlbumsItem?.userId} \n\n"
            tv_1.text = result
        })
    }


}













