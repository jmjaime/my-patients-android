package com.jmj.domain.office

import androidx.lifecycle.LiveData

interface Offices {
    suspend fun findAll(): LiveData<List<Office>>

    suspend fun findById(id: String): LiveData<Office>
}