package br.com.rodorush.cadastrodeativos.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Ativo (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var sigla: String,
    var nome: String,
    var mercado: String
)