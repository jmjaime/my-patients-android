package com.jmj.domain.office

import androidx.lifecycle.LiveData

interface Offices {
    fun findAll(): LiveData<List<Office>>

    fun findById(id: String): LiveData<Office>
}