package org.example.cinestash.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Credits (
    @SerialName("id")
    val id: Int,
    @SerialName("cast")
    val cast : List<CastMember>
)

@Serializable
data class CastMember(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("character")
    val character: String,
    @SerialName("profile_path")
    val profilePath: String?
) {
    val profileUrl: String
        get() = profilePath?.let { "https://image.tmdb.org/t/p/w500$it" }?:""
}