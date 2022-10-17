package com.rafaelleal.android.turmasdatabaseproject.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Anotações para o banco de dados:
 *
 * @Entity(tableName="Nome da Tabela") -> Diz o nome da tabela para a entidade
 *
 * @PrimaryKey -> Diz que a variável anotada é uma chave primária
 *
 * @ColumnInfo(name = "Nome da Coluna") -> Diz o nome da coluna na tabela
 *
 *
 */

@Entity(tableName="Turma" )
data class Turma(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0L,

    @ColumnInfo(name = "nome")
    val nome: String  ="",

    @ColumnInfo(name = "professor")
    val professor: String = "",

    @ColumnInfo(name = "horario")
    val horario: String = ""
)
