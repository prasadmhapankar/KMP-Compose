package data

import data.dto.EpicImagery
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.utils.io.CancellationException

interface NasaApi {
    suspend fun getData(): List<EpicImagery>
}

class KtorNasaApi(private val client: HttpClient) : NasaApi {

    companion object {
        private const val apiKey = "JXu89TwXfKLYuBZy6fCXIbpX1aOJ5b5W3D4uEcx4"
        private const val API_URL =
            "https://api.nasa.gov/EPIC/api/natural?api_key=${apiKey}"
    }

    override suspend fun getData(): List<EpicImagery> {
        return try {
            client.get(API_URL).body()
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            e.printStackTrace()

            emptyList()
        }
    }
}