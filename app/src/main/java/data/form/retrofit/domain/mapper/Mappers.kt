package data.form.retrofit.domain.mapper

import data.form.retrofit.data.dto.ImageDTO
import data.form.retrofit.domain.model.ImageModel

fun ImageDTO.toImageModel() = ImageModel(url = this.url)
