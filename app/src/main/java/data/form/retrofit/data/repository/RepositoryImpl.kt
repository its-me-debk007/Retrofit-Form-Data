package data.form.retrofit.data.repository

import data.form.retrofit.data.network.ApiService
import data.form.retrofit.domain.mapper.toImageModel
import data.form.retrofit.domain.model.ImageModel
import data.form.retrofit.domain.repository.Repository
import okhttp3.MultipartBody
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: ApiService) : Repository {

    override suspend fun uploadImage(
        file: MultipartBody.Part,
        api_key: MultipartBody.Part,
        timestamp: MultipartBody.Part,
        signature: MultipartBody.Part
    ): ImageModel = apiService.uploadImage(file, api_key, timestamp, signature).toImageModel()
}
