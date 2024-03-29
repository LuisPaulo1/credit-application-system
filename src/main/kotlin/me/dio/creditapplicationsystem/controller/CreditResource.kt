package me.dio.creditapplicationsystem.controller

import jakarta.validation.Valid
import me.dio.creditapplicationsystem.dto.CreditDto
import me.dio.creditapplicationsystem.dto.CreditView
import me.dio.creditapplicationsystem.dto.CreditViewList
import me.dio.creditapplicationsystem.entity.Credit
import me.dio.creditapplicationsystem.service.impl.CreditService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import java.util.UUID

@Controller
@RequestMapping("/api/credits")
class CreditResource(private val creditService: CreditService) {

  @PostMapping
  fun saveCredit(@RequestBody @Valid creditDto: CreditDto): ResponseEntity<String> {
    val credit: Credit = this.creditService.save(creditDto.toEntity())
    return ResponseEntity.status(HttpStatus.CREATED)
      .body("Credit ${credit.creditCode} - Customer ${credit.customer?.email} saved!")
  }

  @GetMapping
  fun findAllByCustomerId(@RequestParam(value = "customerId") customerId: Long): ResponseEntity<List<CreditViewList>> {
    val creditViewList: List<CreditViewList> = creditService.findAllByCustomer(customerId).map { CreditViewList(it) }
    return ResponseEntity.ok(creditViewList)
  }

  @GetMapping("/{creditCode}")
  fun findByCreditCode(
    @RequestParam(value = "customerId") customerId: Long,
    @PathVariable creditCode: UUID
  ): ResponseEntity<CreditView> {
    val credit: Credit = this.creditService.findByCreditCode(customerId, creditCode)
    return ResponseEntity.status(HttpStatus.OK).body(CreditView(credit))
  }
}