package me.dio.creditapplicationsystem.entity

import jakarta.persistence.*

@Entity
data class Customer(

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long? = null,

  @Column(nullable = false)
  var firstname: String = "",

  @Column(nullable = false)
  var lastname: String = "",

  @Column(nullable = false, unique = true)
  val cpf: String,

  @Column(nullable = false, unique = true)
  var email: String = "",

  @Column(nullable = false)
  var password: String = "",

  @Embedded
  @Column(nullable = false)
  var address: Address = Address(),

  @Column(nullable = false)
  @OneToMany(mappedBy = "customer", cascade = [CascadeType.REMOVE, CascadeType.PERSIST])
  var credits: List<Credit> = mutableListOf()
)
