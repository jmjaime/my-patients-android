package com.jmj.inmemoryrepository.office

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jmj.domain.office.Office
import com.jmj.domain.office.Offices

class InMemoryOffices(private val offices: MutableMap<String, Office> = mutableMapOf()) :
    Offices {

    override fun findAll(): LiveData<List<Office>> = MutableLiveData(offices.values.toList())

    override fun findById(id: String): LiveData<Office> =
        offices[id]?.let { MutableLiveData(it) } ?: MutableLiveData()
}