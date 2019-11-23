package com.jmj.domain.source

import com.jmj.domain.money.Money
import java.math.BigDecimal

data class PatientSource(val id: String,
                         val source: String,
                         val fee: Money,
                         val tax: BigDecimal) {


    init {
        require(id.isNotBlank()) { "Id can not be blank" }
        require(source.isNotBlank()) { "Source can not be blank" }
        require(tax >= BigDecimal.ZERO && tax <= BigDecimal.valueOf(100)) { "Tax should be between 0 and 100" }
    }
}