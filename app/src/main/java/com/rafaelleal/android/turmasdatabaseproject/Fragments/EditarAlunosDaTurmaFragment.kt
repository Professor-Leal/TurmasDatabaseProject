package com.rafaelleal.android.turmasdatabaseproject.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.rafaelleal.android.turmasdatabaseproject.Fragments.adapters.*
import com.rafaelleal.android.turmasdatabaseproject.databinding.FragmentEditarAlunosDaTurmaBinding
import com.rafaelleal.android.turmasdatabaseproject.models.Aluno
import com.rafaelleal.android.turmasdatabaseproject.models.Turma
import com.rafaelleal.android.turmasdatabaseproject.models.TurmaAluno
import com.rafaelleal.android.turmasdatabaseproject.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class EditarAlunosDaTurmaFragment : Fragment() {

    val TAG = "Escola"

    val viewModel: MainViewModel by activityViewModels()

    var turmaSelecionada = Turma()

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // ViewBinding     /////////////////////////////////////////////////////////////////////////////
    // https://developer.android.com/topic/libraries/view-binding?hl=pt-br#fragments  //////////////
    private var _binding: FragmentEditarAlunosDaTurmaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditarAlunosDaTurmaBinding.inflate(inflater, container, false)
        val view = binding.root

        setup()

        return view
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // RecyclerView ////////////////////////////////////////////////////////////////////////////////
    val adapterQuery = QueryAlunosAdapter(
        object : QueryAlunoListener {
            override fun onAddClick(aluno: Aluno) {
                adicionarAlunoNaTurma(aluno)
            }
        }
    )

    val adapterNaTurma = AlunosNaTurmaAdapter(
        object : AlunosNaTurmaListener {
            override fun onDeleteClick(aluno: Aluno) {
                removerAlunoDaTurma(aluno)
            }
        }
    )

    fun setupQueryRecyclerView() {
        binding.rvQueryAlunos.adapter = adapterQuery
        binding.rvQueryAlunos.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
    }

    fun setupNaTurmaRecyclerView() {
        binding.rvAlunosInseridos.adapter = adapterNaTurma
        binding.rvAlunosInseridos.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
    }

    fun queryAlunos(input: String){
        viewModel.collectAlunosByName(input)
    }

    fun adicionarAlunoNaTurma(aluno: Aluno){
        val newTurmaAluno = TurmaAluno(
            turmaId = turmaSelecionada.id,
            alunoId = aluno.id
        )
        viewModel.insertTurmaAluno(newTurmaAluno)
    }

    fun removerAlunoDaTurma(aluno: Aluno){
        val turmaId = turmaSelecionada.id
        val alunoId = aluno.id
        viewModel.deleteTurmaAluno(turmaId, alunoId)
    }

    private fun setupRecyclerView() {
        setupQueryRecyclerView()
        setupNaTurmaRecyclerView()
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                // Use Collect para receber um StateFlow
                // import kotlinx.coroutines.flow.collect
                viewModel.alunosNaTurma.collect { alunos ->
                    adapterNaTurma.submitList(alunos)
                    binding.rvAlunosInseridos.adapter = adapterNaTurma
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                // Use Collect para receber um StateFlow
                // import kotlinx.coroutines.flow.collect
                viewModel.alunosByName.collect { alunos ->
                    adapterQuery.submitList(alunos)
                    binding.rvQueryAlunos.adapter = adapterQuery
                }
            }
        }
    }


    private fun setup() {
        setupViews()
        setupObservers()
        setupRecyclerView()
        setupDoAfterTextChanged()
        queryAlunos("")
    }

    private fun setupViews() {
        getActivity()?.setTitle("Editar Alunos na Turma");
    }

    fun setupObservers() {
        viewModel.selectedTurmaId.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                turmaSelecionada = viewModel.getTurmaById(it)
                binding.tvTitulo.text = "Turma: ${turmaSelecionada.nome}"
                viewModel.collectAlunosNaTurma(
                    turmaSelecionada.id
                )
            }
        }
    }

    fun setupDoAfterTextChanged(){
        binding.inputQueryAlunos.doAfterTextChanged {
            val input = it.toString()
            queryAlunos(input)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}