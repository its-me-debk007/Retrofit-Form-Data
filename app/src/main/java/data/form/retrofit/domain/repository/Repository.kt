package data.form.retrofit.domain.repository

import data.form.retrofit.domain.model.ImageModel
import okhttp3.MultipartBody

interface Repository {

    suspend fun uploadImage(
        file: MultipartBody.Part,
        api_key: MultipartBody.Part,
        timestamp: MultipartBody.Part,
        signature: MultipartBody.Part
    ): ImageModel
}
