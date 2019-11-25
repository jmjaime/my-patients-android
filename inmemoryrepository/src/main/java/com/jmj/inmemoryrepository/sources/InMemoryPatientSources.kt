package com.jmj.inmemoryrepository.sources

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jmj.domain.source.PatientSource
import com.jmj.domain.source.PatientSources

class InMemoryPatientSources(private val patientSources: MutableMap<String, PatientSource> = mutableMapOf()) :
    PatientSources {

    override suspend fun findAll(): LiveData<List<PatientSource>> = MutableLiveData(patientSources.values.toList())

    override suspend fun findById(id: String): LiveData<PatientSource> =
        patientSources[id]?.let { MutableLiveData(it) } ?: MutableLiveData()
}