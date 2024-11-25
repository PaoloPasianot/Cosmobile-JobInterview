package it.paolopasianot.cosmobile.core.network.dto.request.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class LoginRequestDTO(
  @SerialName("username")
  val username: String,
  @SerialName("password")
  val password: String
)
