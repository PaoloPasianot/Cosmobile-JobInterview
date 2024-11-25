package it.paolopasianot.cosmobile.core.network.dto.response.auht

import it.paolopasianot.cosmobile.model.types.JWTToken
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDTO(
  @SerialName("auth_token")
  val token: JWTToken
)
