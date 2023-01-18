package data.form.retrofit.presentation.screen

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import data.form.retrofit.common.ApiState
import data.form.retrofit.presentation.viewmodel.FormDataViewModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ImagePicker(viewModel: FormDataViewModel = hiltViewModel()) {

    val context = LocalContext.current
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val imageLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()) {
            it?.let { uri ->
                imageUri = uri
                viewModel.uploadFile(context, uri)
            } ?: run {
                Toast.makeText(context, "Please select an image", Toast.LENGTH_SHORT).show()
            }
        }

    Column(
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp)
    ) {
        Button(
            onClick = { imageLauncher.launch(PickVisualMediaRequest()) },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            if (viewModel.state.value is ApiState.Loading) CircularProgressIndicator(
                modifier = Modifier.size(26.dp),
                color = Color.Green
            )
            else Text(text = "Pick an Image")
        }

        viewModel.state.value.let {
            val text = when (it) {

                is ApiState.Success -> it.data!!.url

                is ApiState.Error -> it.errorMsg!!

                else -> ""
            }

            Text(
                text = text,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
            )
        }

        imageUri?.let {
            GlideImage(
                model = it,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(296.dp)
            )
        }
    }
}
