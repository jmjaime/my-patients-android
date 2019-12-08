package com.jmj.inmemoryrepository

import com.jmj.domain.money.Money
import com.jmj.domain.office.Office
import com.jmj.domain.office.Offices
import com.jmj.domain.patient.Patient
import com.jmj.domain.source.PatientSource
import com.jmj.domain.source.PatientSources
import com.jmj.domain.treatment.Derivation
import com.jmj.domain.treatment.Treatment
import com.jmj.domain.treatment.Treatments
import com.jmj.inmemoryrepository.office.InMemoryOffices
import com.jmj.inmemoryrepository.sources.InMemoryPatientSources
import com.jmj.inmemoryrepository.treatment.InMemoryTreatments
import org.koin.dsl.module
import java.math.BigDecimal

val inMemoryRepositoryModule = module {
    single<Offices> { InMemoryOffices(offices = defaultOffices()) }
    single<PatientSources> { InMemoryPatientSources(patientSources = defaultPatientSources()) }
    single<Treatments> { InMemoryTreatments(treatments = defaultTreatments()) }
}


private fun defaultOffices() = mutableMapOf(
    officeVirtual().let { it.id to it },
    "2" to Office("2", "Consultorio")
)

private fun officeVirtual() = Office("1", "Virtual")

private fun defaultPatientSources() = mutableMapOf(
    patientSourceParticular().let { it.id to it },
    "2" to PatientSource("2", "Osde", Money(200), BigDecimal.ZERO)
)

private fun patientSourceParticular() =
    PatientSource("1", "Particular", Money(100), BigDecimal.ZERO)

private fun defaultTreatments() = mutableMapOf(
    "25" to Treatment(
        id = "25",
        patient = Patient(id = "44", name = "Jose Perez"),
        defaultOffice = officeVirtual(),
        derivation = Derivation(
            patientSource = patientSourceParticular(),
            currentFee = Money(100)
        )
    )
)