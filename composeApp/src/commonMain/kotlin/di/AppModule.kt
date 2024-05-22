package di

import data.EpicImageryRepository
import data.EpicImageryRepositoryImpl
import data.KtorNasaApi
import data.NasaApi
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val appModule = module {
    single {
        val json = Json { ignoreUnknownKeys = true }
        HttpClient {
            install(ContentNegotiation) {
                json(json, contentType = ContentType.Any)
            }
        }
    }

    single<NasaApi> { KtorNasaApi(get()) }
    single<EpicImageryRepository> { EpicImageryRepositoryImpl(get()) }
}