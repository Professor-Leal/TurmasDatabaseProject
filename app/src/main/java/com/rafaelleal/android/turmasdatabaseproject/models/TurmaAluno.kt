package com.rafaelleal.android.turmasdatabaseproject.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Anotações para o banco de dados:
 *
 * @Entity(tableName="Nome da Tabela") -> Diz o nome da tabela para a entidade
 *
 * foreignKeys -> Associa a coluna de outra entidade com a chave estrangeira desta.
 *
 * @PrimaryKey -> Diz que a variável anotaada é uma chave primária
 *
 * @ColumnInfo(name = "Nome da Coluna") -> Diz o nome da coluna na tabela
 *
 *
 */

@Entity(
    tableName = "TurmaAluno",
    foreignKeys = [
        ForeignKey(
            entity = Turma::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("turmaId"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Aluno::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("alunoId"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class TurmaAluno(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "turmaId")
    val turmaId: Long = 0L,

    @ColumnInfo(name = "alunoId")
    val alunoId: Long = 0L
)
