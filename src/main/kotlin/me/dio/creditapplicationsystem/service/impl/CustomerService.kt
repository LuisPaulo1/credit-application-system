package me.dio.creditapplicationsystem.service.impl

import me.dio.creditapplicationsystem.entity.Customer
import me.dio.creditapplicationsystem.repository.CustomerRepository
import me.dio.creditapplicationsystem.service.ICustomerService
import org.springframework.stereotype.Service

@Service
class CustomerService(private val customerRepository: CustomerRepository ) : ICustomerService {
  override fun save(customer: Customer): Customer = customerRepository.save(customer)
  override fun findById(id: Long): Customer {
    return customerRepository.findById(id).orElseThrow {
      throw RuntimeException("Id $id not found")
    }
  }
  override fun delete(id: Long) = customerRepository.deleteById(id)
}