package com.jmj.repository.remote.office

import com.jmj.domain.office.Office
import com.jmj.domain.office.Offices

class RemoteOffices(private val retrofitOffices: RetrofitOffices) : Offices {

    override suspend fun findAll(): List<Office> = retrofitOffices.findAll().map { it.toOffice() }

    override suspend fun findById(id: String): Office? = retrofitOffices.findById(id)?.toOffice()

}

private fun RemoteOffice.toOffice() = Office(id = this.id, name = this.name)