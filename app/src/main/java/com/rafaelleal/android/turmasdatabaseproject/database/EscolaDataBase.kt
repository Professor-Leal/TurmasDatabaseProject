package com.rafaelleal.android.turmasdatabaseproject.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rafaelleal.android.Alunosdatabaseproject.daos.AlunoDao
import com.rafaelleal.android.TurmaAlunosdatabaseproject.daos.TurmaAlunoDao
import com.rafaelleal.android.turmasdatabaseproject.daos.TurmaDao
import com.rafaelleal.android.turmasdatabaseproject.models.Aluno
import com.rafaelleal.android.turmasdatabaseproject.models.Turma
import com.rafaelleal.android.turmasdatabaseproject.models.TurmaAluno

/**
 * Anotações para o banco de dados:
 *
 * @Database -> Define o banco de dados e as entidades dele
 */

@Database(entities = [Turma::class, Aluno::class, TurmaAluno::class ], version = 1 , exportSchema = false )
abstract  class EscolaDataBase: RoomDatabase() {

    // Interfaces que podem alterar o banco de dados:
    abstract fun  turmaDao() : TurmaDao
    abstract fun  alunoDao() : AlunoDao
    abstract fun turmaAlunoDao() : TurmaAlunoDao


}