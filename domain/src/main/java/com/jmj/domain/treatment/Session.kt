package com.jmj.domain.treatment

import com.jmj.domain.money.Money
import com.jmj.domain.office.Office
import java.time.LocalDate

data class Session(val number: Int, val date: LocalDate, val office: Office, val fee:Money)