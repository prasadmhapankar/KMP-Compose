package di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import presentation.feature_details.FeatureDetailsViewModel
import presentation.feature_list.FeatureListViewModel

actual val viewModelModule = module {
    singleOf(::FeatureListViewModel)
    singleOf(::FeatureDetailsViewModel)
}