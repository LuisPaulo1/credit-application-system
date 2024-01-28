package me.dio.creditapplicationsystem.controller

import me.dio.creditapplicationsystem.dto.CreditDto
import me.dio.creditapplicationsystem.entity.Credit
import me.dio.creditapplicationsystem.service.impl.CreditService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/api/credits")
class CreditResource(private val creditService: CreditService) {

  @PostMapping
  fun saveCredit(@RequestBody creditDto: CreditDto): String {
    val credit: Credit = creditService.save(creditDto.toEntity())
    return "Credit ${credit.creditCode} - Customer ${credit.customer?.firstName} saved!"
  }
}