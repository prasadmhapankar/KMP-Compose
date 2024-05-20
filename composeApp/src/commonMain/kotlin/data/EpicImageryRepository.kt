package data

import data.dto.EpicImagery

interface EpicImageryRepository {
    suspend fun getEpicImagery(): List<EpicImagery>
}