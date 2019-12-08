package com.jmj.domain.action.model

import com.jmj.domain.office.Office

data class OfficeModel(
    val id: String,
    val name: String
)

fun Office.toModel() = OfficeModel(
    id = this.id,
    name = this.name
)
