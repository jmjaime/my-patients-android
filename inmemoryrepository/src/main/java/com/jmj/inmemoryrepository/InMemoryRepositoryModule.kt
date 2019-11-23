package com.jmj.inmemoryrepository

import com.jmj.domain.money.Money
import com.jmj.domain.office.Office
import com.jmj.domain.office.Offices
import com.jmj.domain.source.PatientSource
import com.jmj.domain.source.PatientSources
import com.jmj.domain.treatment.Treatments
import com.jmj.inmemoryrepository.office.InMemoryOffices
import com.jmj.inmemoryrepository.sources.InMemoryPatientSources
import com.jmj.inmemoryrepository.treatment.InMemoryTreatments
import org.koin.dsl.module
import java.math.BigDecimal

val inMemoryRepositoryModule = module {
    single<Offices> { InMemoryOffices(offices = defaultOffices()) }
    single<PatientSources> { InMemoryPatientSources(patientSources = defaultPatientSources()) }
    single<Treatments> {InMemoryTreatments()}
}


private fun defaultOffices() = mutableMapOf(
    "1" to Office("1", "Virtual"),
    "2" to Office("2", "Consultorio")
)

private fun defaultPatientSources() = mutableMapOf(
    "1" to PatientSource("1", "Particular", Money.ZERO, BigDecimal.ZERO),
    "2" to PatientSource("2", "Osde", Money(200), BigDecimal.ZERO)
)