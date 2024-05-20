package di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import presentation.feature_details.FeatureDetailsViewModel
import presentation.feature_list.FeatureListViewModel

actual val viewModelModule = module{
    viewModelOf(::FeatureListViewModel)
    viewModelOf(::FeatureDetailsViewModel)
}