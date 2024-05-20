package presentation.feature_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.EpicImageryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FeatureListViewModel(
    private val epicImageryRepository: EpicImageryRepository
) : ViewModel() {

    private val _state = MutableStateFlow(EpicImageryListState())
    val featureListState = _state.asStateFlow()

    init {
        getEpicImageryData()
    }

    private fun getEpicImageryData() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = epicImageryRepository.getEpicImagery()
            println("NASA response - $response")
            _state.update {
                it.copy(list = response)
            }
        }
    }
}