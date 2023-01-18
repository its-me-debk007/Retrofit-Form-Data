package data.form.retrofit.data.network

import data.form.retrofit.data.dto.ImageDTO
import retrofit2.http.POST

interface ApiService {

    @POST("/image/upload")
    suspend fun uploadImage(): ImageDTO

}
