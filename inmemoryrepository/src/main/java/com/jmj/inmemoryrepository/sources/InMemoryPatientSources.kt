package com.jmj.inmemoryrepository.sources

import com.jmj.domain.source.PatientSource
import com.jmj.domain.source.PatientSources

class InMemoryPatientSources(private val patientSources: MutableMap<String, PatientSource> = mutableMapOf()) :
    PatientSources {

    override suspend fun findAll(): List<PatientSource> = patientSources.values.toList()

    override suspend fun findById(id: String): PatientSource? = patientSources[id]
}