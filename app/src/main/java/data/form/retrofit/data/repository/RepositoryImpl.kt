package data.form.retrofit.data.repository

import data.form.retrofit.data.network.ApiService
import data.form.retrofit.domain.mapper.toImageModel
import data.form.retrofit.domain.model.ImageModel
import data.form.retrofit.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: ApiService) : Repository {

    override suspend fun uploadImage(): ImageModel = apiService.uploadImage().toImageModel()
}
