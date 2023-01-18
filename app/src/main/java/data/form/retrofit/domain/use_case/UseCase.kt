package data.form.retrofit.domain.use_case

import data.form.retrofit.common.ApiState
import data.form.retrofit.domain.model.ImageModel
import data.form.retrofit.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UseCase @Inject constructor(private val repository: Repository) {

    operator fun invoke(): Flow<ApiState<ImageModel>> = flow {
        emit(ApiState.Loading())

        try {
            emit(ApiState.Success(repository.uploadImage()))

        } catch (e: java.lang.Exception) {
            emit(ApiState.Error(e.message.toString()))
        }
    }
}
