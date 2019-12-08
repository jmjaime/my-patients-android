package com.jmj.domain.source

interface PatientSources {

    suspend fun findAll(): List<PatientSource>

    suspend fun findById(id: String): PatientSource?
}