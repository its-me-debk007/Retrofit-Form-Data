package data.form.retrofit.data.network

import data.form.retrofit.data.dto.ImageDTO
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @Multipart
    @POST("image/upload")
    suspend fun uploadImage(
        @Part file: MultipartBody.Part,
        @Part api_key: MultipartBody.Part,
        @Part timestamp: MultipartBody.Part,
        @Part signature: MultipartBody.Part
    ): ImageDTO

}
