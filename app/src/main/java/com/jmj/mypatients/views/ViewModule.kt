package com.jmj.mypatients.views

import com.jmj.mypatients.views.treatment.InitTreatmentViewModel
import com.jmj.mypatients.views.treatment.TreatmentViewModel
import com.jmj.mypatients.views.treatment.TreatmentsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        InitTreatmentViewModel(
            initTreatment = get(),
            offices = get(),
            patientSources = get()
        )
    }
    viewModel { TreatmentsViewModel(treatments = get()) }
    viewModel { TreatmentViewModel() }
}