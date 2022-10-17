package com.rafaelleal.android.turmasdatabaseproject.database

import android.content.Context
import androidx.room.Room
import com.rafaelleal.android.turmasdatabaseproject.models.Turma


/**
 * Guia para a arquitetura do app:
 * https://developer.android.com/jetpack/guide
 *
 */

private const val DATABASE_NAME = "escola-db"

class EscolaRepository private constructor(context: Context) {

    private val database: EscolaDataBase = Room
        .databaseBuilder(
            context.applicationContext,
            EscolaDataBase::class.java,
            DATABASE_NAME
        )
        .build()

    companion object {
        private var INSTANCE: EscolaRepository? = null
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = EscolaRepository(context)
            }
        }

        fun get(): EscolaRepository {
            return INSTANCE
                ?: throw IllegalStateException("EscolaRepository deve ser inicializado.")
        }
    }

    suspend fun getAllTurmas(): List<Turma> = database.turmaDao().getAll()

    suspend fun insertTurma(turma: Turma) {
        database.turmaDao().insert(turma)
    }


}