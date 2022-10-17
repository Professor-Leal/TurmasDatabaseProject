package com.rafaelleal.android.turmasdatabaseproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rafaelleal.android.turmasdatabaseproject.databinding.ActivityMainBinding

/**
 * Objetivo: Criar um banco de dados de turmas e alunos
 *
 * Passo 01: Criar os modelos
 *
 * Passo 02: Criar os DAO
 *
 * Passo 03: Criar o banco de dados
 *
 * Referências:
 *
 * Salvar dados em um banco de dados local usando o Room
 * https://developer.android.com/training/data-storage/room
 *
 *
 */

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }


}