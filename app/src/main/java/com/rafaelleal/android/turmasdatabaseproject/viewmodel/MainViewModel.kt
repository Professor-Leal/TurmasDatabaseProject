package com.rafaelleal.android.turmasdatabaseproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafaelleal.android.turmasdatabaseproject.database.EscolaRepository
import com.rafaelleal.android.turmasdatabaseproject.models.Turma
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val escolaRepository = EscolaRepository.get()

    fun insertTurma(turma: Turma) {
        viewModelScope.launch(Dispatchers.IO) {
            escolaRepository.insertTurma(turma)
        }
    }

    // StateFlow e SharedFlow
    // https://developer.android.com/kotlin/flow/stateflow-and-sharedflow
    // Recebe turmas do banco de dados como StateFlow e permanece a última pesquisa feita
    private val _turmas: MutableStateFlow<List<Turma>> = MutableStateFlow(emptyList())
    val turmas: StateFlow<List<Turma>>
        get() = _turmas.asStateFlow()


    // Inicia o viewModel
    init {
        viewModelScope.launch(Dispatchers.IO)  {
            escolaRepository.getAllTurmas().collect {
                _turmas.value = it
            }
        }
    }



// // Usando Livedata:
//    private val _listaTurmas: MutableLiveData<List<Turma>> by lazy {
//        MutableLiveData<List<Turma>>()
//    }
//    val listaTurmas: LiveData<List<Turma>> = _listaTurmas
//
//    fun setListaTurmas(value: List<Turma>){
//        _listaTurmas.postValue(value)
//    }
//    fun fetchTurmas() {
//        viewModelScope.launch(Dispatchers.IO){
//            setListaTurmas(escolaRepository.getAllTurmas())
//        }
//    }

//    // Usando Flow Pega os dados do banco a cada chamada
//    val listaTurmas = escolaRepository.getAllTurmas()


}