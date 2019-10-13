package com.lamadridblandongoyes.domain.models

data class Note(
    val noteId: Int,
    val entityId: Int? = null,
    val title: String,
    val description: String,
    val reminder: String? = null
)