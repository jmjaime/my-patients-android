package com.jmj.domain.source

import androidx.lifecycle.LiveData

interface PatientSources {

    suspend fun findAll(): LiveData<List<PatientSource>>

    suspend fun findById(id: String): LiveData<PatientSource>
}