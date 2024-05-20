package data

import data.dto.EpicImagery

class EpicImageryRepositoryImpl(
    private val nasaApi: NasaApi,
): EpicImageryRepository {

    override suspend fun getEpicImagery(): List<EpicImagery>  = nasaApi.getData()
}