package me.dio.creditapplicationsystem.controller

import me.dio.creditapplicationsystem.dto.CustomerDto
import me.dio.creditapplicationsystem.entity.Customer
import me.dio.creditapplicationsystem.service.impl.CustomerService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/api/customers")
class CustomerResource(private val customerService: CustomerService) {

  @PostMapping
  fun saveCustomer(@RequestBody customerDto: CustomerDto): String {
    val savedCustomer = customerService.save(customerDto.toEntity())
    return "Customer ${savedCustomer.email} saved!"
  }

  @GetMapping("/{id}")
  fun findById(@PathVariable id: Long): CustomerView {
    val customer: Customer = customerService.findById(id)
    return CustomerView(customer)
  }

  @DeleteMapping("/{id}")
  fun delete(@PathVariable id: Long) = customerService.delete(id)
}