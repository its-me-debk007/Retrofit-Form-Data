package data.form.retrofit.domain.use_case

import android.util.Log
import data.form.retrofit.BuildConfig
import data.form.retrofit.common.ApiState
import data.form.retrofit.domain.model.ImageModel
import data.form.retrofit.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.security.MessageDigest
import javax.inject.Inject

class UploadImageUseCase @Inject constructor(private val repository: Repository) {

    operator fun invoke(
        file: File
    ): Flow<ApiState<ImageModel>> = flow {

        emit(ApiState.Loading())

        try {
            val apiKey = MultipartBody.Part.createFormData("api_key", BuildConfig.API_KEY)

            val timeSinceEpoch = System.currentTimeMillis() / 1000
            Log.d("SHA1", timeSinceEpoch.toString())
            val timeStamp =
                MultipartBody.Part.createFormData("timestamp", "1674062767")

            val str = "timestamp=$timeStamp${BuildConfig.API_SECRET}"

            val signature = MultipartBody.Part.createFormData(
                "signature",
                "8e30cbdf20544e455bc2a404a8536814940b6743"
            )
            Log.d("SHA1", str.toSHA1())

            val requestBody = RequestBody.create(MediaType.parse("image/*"), file)
            val fileMultipart = MultipartBody.Part.createFormData("file", file.name, requestBody)

            emit(
                ApiState.Success(
                    repository.uploadImage(
                        fileMultipart,
                        apiKey,
                        timeStamp,
                        signature
                    )
                )
            )

        } catch (e: java.lang.Exception) {
            emit(ApiState.Error(e.message.toString()))
        }
    }
}

fun String.toSHA1() = MessageDigest
    .getInstance("SHA-1")
    .digest(this.toByteArray())
    .joinToString("") { "%02x".format(it) }
