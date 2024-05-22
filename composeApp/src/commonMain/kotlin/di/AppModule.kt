package di

import data.EpicImageryRepository
import data.EpicImageryRepositoryImpl
import data.KtorNasaApi
import data.NasaApi
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.logging.Logger
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val appModule = module {
    single {
        val json = Json { ignoreUnknownKeys = true }
        HttpClient {
            install(ContentNegotiation) {
                json(json, contentType = ContentType.Any)
            }

            //Http Response
            install(Logging) {
                logger = object : io.ktor.client.plugins.logging.Logger {
                    override fun log(message: String) {
                        println("HttpLogging:$message")
                    }
                }
                level = LogLevel.BODY
            }

            // Default Headers
            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
        }
    }

    single<NasaApi> { KtorNasaApi(get()) }
    single<EpicImageryRepository> { EpicImageryRepositoryImpl(get()) }
}