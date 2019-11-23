package com.jmj.domain.source

import androidx.lifecycle.LiveData

interface PatientSources {

    fun findAll(): LiveData<List<PatientSource>>

    fun findById(id: String): LiveData<PatientSource>
}