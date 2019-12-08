package com.jmj.inmemoryrepository.office

import com.jmj.domain.office.Office
import com.jmj.domain.office.Offices

class InMemoryOffices(private val offices: MutableMap<String, Office> = mutableMapOf()) :
    Offices {

    override suspend fun findAll(): List<Office> = offices.values.toList()

    override suspend fun findById(id: String): Office? = offices[id]
}