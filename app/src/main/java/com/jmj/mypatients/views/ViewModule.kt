package com.jmj.mypatients.views

import com.jmj.mypatients.views.treatment.InitTreatmentViewModel
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
}