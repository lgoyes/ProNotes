package com.lamadridblandongoyes.domain.models

data class Note(
    val noteId: Int,
    val labelId: Int? = null,
    val title: String,
    val description: String,
    val reminder: String? = null
)