package me.dio.creditapplicationsystem.entity

import jakarta.persistence.*
import me.dio.creditapplicationsystem.entity.enummeration.Status
import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID

@Entity
data class Credit (

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long? = null,

  @Column(nullable = false, unique = true)
  val creditCode: UUID = UUID.randomUUID(),

  @Column(nullable = false)
  val creditValue: BigDecimal = BigDecimal.ZERO,

  @Column(nullable = false)
  val dayFirstInstallment: LocalDate,

  @Column(nullable = false)
  val numberOfInstallments: Int = 0,

  @Enumerated(EnumType.STRING)
  val status: Status = Status.IN_PROGRESS,

  @ManyToOne
  @JoinColumn(name = "customer_id", nullable = false)
  var customer: Customer? = null
)
