package com.jmj.domain.office

interface Offices {
    suspend fun findAll(): List<Office>

    suspend fun findById(id: String): Office?
}