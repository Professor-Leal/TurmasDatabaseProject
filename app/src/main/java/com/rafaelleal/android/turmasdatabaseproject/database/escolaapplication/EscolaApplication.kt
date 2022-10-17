package com.rafaelleal.android.turmasdatabaseproject.database.escolaapplication

import android.app.Application
import com.rafaelleal.android.turmasdatabaseproject.database.EscolaRepository


/**
 * Classe criada para gerar uma única instancia do repositório quando o app for inicializado
 *
 * Manifesto deve ser alterado
 * Adicione:
 *         android:name=".database.escolaapplication.EscolaApplication"
 *  dentro da tag <application>
 */
class EscolaApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        EscolaRepository.initialize(this)
    }
}