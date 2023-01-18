package data.form.retrofit.presentation.viewmodel

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import data.form.retrofit.common.ApiState
import data.form.retrofit.domain.model.ImageModel
import data.form.retrofit.domain.use_case.UploadImageUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

@HiltViewModel
class FormDataViewModel @Inject constructor(private val uploadImageUseCase: UploadImageUseCase): ViewModel() {

    private val _state = mutableStateOf<ApiState<ImageModel>>(ApiState.Error("Uploaded image URL will be displayed here"))
    val state: State<ApiState<ImageModel>> = _state

    fun uploadFile(context: Context, uri: Uri) {
        val file = File(context.filesDir, "image.png")

        val inputStream = context.contentResolver.openInputStream(uri)
        val outputStream = FileOutputStream(file)

        inputStream!!.copyTo(outputStream)

        uploadImageUseCase(file).onEach {
            _state.value = when(it) {
                is ApiState.Loading -> ApiState.Loading()

                is ApiState.Error -> ApiState.Error(it.errorMsg.toString())

                is ApiState.Success -> ApiState.Success(it.data)
            }
        }.launchIn(viewModelScope)

        inputStream.close()
    }
}
