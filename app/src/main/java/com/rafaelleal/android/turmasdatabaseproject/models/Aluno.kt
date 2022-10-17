package com.rafaelleal.android.turmasdatabaseproject.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Anotações para o banco de dados:
 *
 * @Entity(tableName="Nome da Tabela") -> Diz o nome da tabela para a entidade
 *
 * @PrimaryKey -> Diz que a variável anotaada é uma chave primária
 *
 * @ColumnInfo(name = "Nome da Coluna") -> Diz o nome da coluna na tabela
 *
 *
 */

@Entity(tableName="Aluno" )
data class Aluno(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "nome")
    val nome: String ="",

    @ColumnInfo(name = "matricula")
    val matricula: String = "",

    @ColumnInfo(name = "periodo")
    val periodo: Int = 0
)
