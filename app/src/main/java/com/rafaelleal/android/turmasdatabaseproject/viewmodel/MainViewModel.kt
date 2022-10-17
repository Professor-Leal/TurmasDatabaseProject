package com.rafaelleal.android.turmasdatabaseproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafaelleal.android.turmasdatabaseproject.database.EscolaRepository
import com.rafaelleal.android.turmasdatabaseproject.models.Turma
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val escolaRepository = EscolaRepository.get()

    private val _listaTurmas: MutableLiveData<List<Turma>> by lazy {
        MutableLiveData<List<Turma>>()
    }
    val listaTurmas: LiveData<List<Turma>> = _listaTurmas

    fun setListaTurmas(value: List<Turma>){
        _listaTurmas.postValue(value)
    }

    fun fetchTurmas() {
        viewModelScope.launch(Dispatchers.IO){
            setListaTurmas(escolaRepository.getAllTurmas())
        }
    }

    fun insertTurma(turma: Turma){
        viewModelScope.launch(Dispatchers.IO) {
            escolaRepository.insertTurma(turma)
        }
    }




}