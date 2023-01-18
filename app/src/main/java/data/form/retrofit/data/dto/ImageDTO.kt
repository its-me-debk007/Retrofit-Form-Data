package data.form.retrofit.data.dto

data class ImageDTO(
    val api_key: String,
    val asset_id: String,
    val bytes: Int,
    val created_at: String,
    val etag: String,
    val folder: String,
    val format: String,
    val height: Int,
    val original_filename: String,
    val placeholder: Boolean,
    val public_id: String,
    val resource_type: String,
    val secure_url: String,
    val signature: String,
    val tags: List<Any>,
    val type: String,
    val url: String,
    val version: Int,
    val version_id: String,
    val width: Int
)
