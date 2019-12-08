package com.jmj.domain.action.model

import com.jmj.domain.money.Money
import com.jmj.domain.source.PatientSource
import java.math.BigDecimal

data class PatientSourceModel(
    val id: String,
    val source: String,
    val fee: Money,
    val tax: BigDecimal
)

fun PatientSource.toModel() = PatientSourceModel(
    id = this.id,
    source = this.source,
    fee = this.fee,
    tax = this.tax
)