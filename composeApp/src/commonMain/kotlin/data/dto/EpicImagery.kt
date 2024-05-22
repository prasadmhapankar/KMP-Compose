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

fun EpicImagery.getImageUrl(): String {
    val imageDate = date?.split(" ")?.get(0)?.replace("-", "/") ?: "2024/05/05"
    return "https://epic.gsfc.nasa.gov/archive/natural/${imageDate}/png/${image}.png"
}
