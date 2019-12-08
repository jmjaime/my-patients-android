package com.jmj.domain

import com.jmj.domain.action.*
import com.jmj.domain.id.IdGenerator
import org.koin.dsl.module
import java.util.*

val domainModule = module {
    single {
        InitTreatment(
            treatments = get(),
            patientSources = get(),
            offices = get(),
            idGenerator = UUIDIdGenerator()
        )
    }
    single { FindPatientSources(patientSources = get()) }
    single { FindOffices(offices = get()) }
    single { FindMyTreatments(treatments = get()) }
    single { FindTreatment(treatments = get()) }
}

class UUIDIdGenerator : IdGenerator {
    override fun next() = UUID.randomUUID().toString()
}