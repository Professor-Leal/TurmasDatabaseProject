package com.rafaelleal.android.turmasdatabaseproject.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafaelleal.android.turmasdatabaseproject.database.EscolaRepository
import com.rafaelleal.android.turmasdatabaseproject.models.Aluno
import com.rafaelleal.android.turmasdatabaseproject.models.Turma
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val TAG = "Escola"

    private val escolaRepository = EscolaRepository.get()

    // StateFlow e SharedFlow
    // https://developer.android.com/kotlin/flow/stateflow-and-sharedflow
    // Recebe turmas do banco de dados como StateFlow e permanece a última pesquisa feita
    private val _turmas: MutableStateFlow<List<Turma>> = MutableStateFlow(emptyList())
    val turmas: StateFlow<List<Turma>>
        get() = _turmas.asStateFlow()

    // Guarda a turma selecionada para edição
    private val _selectedTurmaId = MutableLiveData<Long>(0L)
    val selectedTurmaId : LiveData<Long> = _selectedTurmaId
    fun setSelectedTurmaId(value: Long) {
        _selectedTurmaId.setValue(value)
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // CRUD Turma //////////////////////////////////////////////////////////////////////////////////

    fun insertTurma(turma: Turma) {
        viewModelScope.launch(Dispatchers.IO) {
            escolaRepository.insertTurma(turma)
        }
    }

    fun getTurmaById(id: Long): Turma {
        return escolaRepository.getTurmaById(id)
    }

    fun updateTurma(turma:Turma){
        viewModelScope.launch(Dispatchers.IO) {
            escolaRepository.updateTurma(turma)
        }
    }

    fun deleteTurma(turma:Turma){
        viewModelScope.launch(Dispatchers.IO){
            escolaRepository.deleteTurma(turma)
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////









    // StateFlow e SharedFlow
    // https://developer.android.com/kotlin/flow/stateflow-and-sharedflow
    // Recebe turmas do banco de dados como StateFlow e permanece a última pesquisa feita
    private val _alunos: MutableStateFlow<List<Aluno>> = MutableStateFlow(emptyList())
    val alunos: StateFlow<List<Aluno>>
        get() = _alunos.asStateFlow()

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // CRUD Aluno //////////////////////////////////////////////////////////////////////////////////

    fun insertAluno(aluno: Aluno) {
        viewModelScope.launch(Dispatchers.IO) {
            escolaRepository.insertAluno(aluno)
        }
    }

    fun getAlunoById(id: Long): Aluno {
        var aluno: Aluno? = null
        val job = viewModelScope.launch(Dispatchers.IO) {
            val  alunoAsync = async{
                escolaRepository.getAlunoById(id)
            }
            aluno = alunoAsync.await()
        }
        return aluno ?: Aluno()
    }

    fun updateAluno(aluno:Aluno){
        viewModelScope.launch(Dispatchers.IO) {
            escolaRepository.updateAluno(aluno)
        }
    }

    fun deleteAluno(aluno:Aluno){
        viewModelScope.launch(Dispatchers.IO){
            escolaRepository.deleteAluno(aluno)
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////







    // Inicia o viewModel
    init {
        collectAlunos()
        collectTurmas()
    }
    fun collectAlunos(){
        viewModelScope.launch {
            escolaRepository.getAllAlunos().collect{
                _alunos.value = it
            }
        }
    }

    fun collectTurmas(){
        viewModelScope.launch {
            escolaRepository.getAllTurmas().collect {
                _turmas.value = it
            }
        }
    }

    // StateFlow e SharedFlow
    // https://developer.android.com/kotlin/flow/stateflow-and-sharedflow
    // Recebe turmas do banco de dados como StateFlow e permanece a última pesquisa feita
    private val _alunosByName: MutableStateFlow<List<Aluno>> = MutableStateFlow(emptyList())
    val alunosByName: StateFlow<List<Aluno>>
        get() = _alunosByName.asStateFlow()

    // Pesquisando com "%${input}%" vai retornar os que contém input no nome
    fun collectAlunosByName(input: String){
        viewModelScope.launch {
            escolaRepository.getAlunoByName("%${input}%").collect {
                _alunosByName.value = it
                it.forEach {
                    Log.i(TAG, "Aluno: ${it.nome}" )
                }
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