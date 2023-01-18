package data.form.retrofit.domain.repository

import data.form.retrofit.domain.model.ImageModel

interface Repository {

    suspend fun uploadImage(): ImageModel
}
