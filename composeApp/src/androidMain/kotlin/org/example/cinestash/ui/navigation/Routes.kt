package org.example.cinestash.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
object Search

@Serializable
object Profile

@Serializable
data class Detail(val movieId: Int)