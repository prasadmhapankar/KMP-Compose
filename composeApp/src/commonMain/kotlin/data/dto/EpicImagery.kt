package data.dto

import kotlinx.serialization.Serializable

@Serializable
data class EpicImagery(
    val caption: String?,
    val date: String?,
    val identifier: String,
    val image: String?,
    val version: String?
)