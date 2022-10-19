package com.rafaelleal.android.turmasdatabaseproject.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.rafaelleal.android.turmasdatabaseproject.R
import com.rafaelleal.android.turmasdatabaseproject.databinding.FragmentEditarTurmaBinding
import com.rafaelleal.android.turmasdatabaseproject.models.Turma
import com.rafaelleal.android.turmasdatabaseproject.utils.navUp
import com.rafaelleal.android.turmasdatabaseproject.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class EditarTurmaFragment : Fragment() {

    val TAG = "Escola"

    val viewModel: MainViewModel by activityViewModels()


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // ViewBinding     /////////////////////////////////////////////////////////////////////////////
    // https://developer.android.com/topic/libraries/view-binding?hl=pt-br#fragments  //////////////
    private var _binding: FragmentEditarTurmaBinding? = null

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
        _binding = FragmentEditarTurmaBinding.inflate(inflater, container, false)
        val view = binding.root

        setup()

        return view
    }

    private fun setup() {
        setupViews()
        setupClickListeners()
        setupObservers()
    }

    private fun setupViews() {
        getActivity()?.setTitle("Editar Turma")
    }

    fun setupObservers() {
        viewModel.selectedTurmaId.observe(viewLifecycleOwner) {
            lifecycleScope.launch(Dispatchers.IO) {
                val turma = viewModel.getTurmaById(it)
                Log.i(TAG, "turma Selecionada: ${turma.nome} - id: ${turma.id}")
                setupTurmaView(turma)
            }

        }
    }

    suspend fun setupTurmaView(turma: Turma) {
        withContext(Dispatchers.Main) {
            binding.apply {
                inputHorario.setText(turma.horario)
                inputNomeTurma.setText(turma.nome)
                inputNomeProfessor.setText(turma.professor)
            }
        }
    }

    private fun setupClickListeners() {
        binding.apply {
            fabUpdateTurma.setOnClickListener {
                updateTurma()
                navUp()
            }
        }
    }

    fun updateTurma() {
        if (validateInput()) {
            val turma = getUpdateTurmaFromInputs()
            viewModel.updateTurma(turma)
        }
    }

    fun getUpdateTurmaFromInputs(): Turma {
        return Turma(
            id = viewModel.selectedTurmaId.value!!,
            nome = binding.inputNomeTurma.text.toString(),
            professor = binding.inputNomeProfessor.text.toString(),
            horario = binding.inputHorario.text.toString()
        )
    }

    fun clearInputs() {
        binding.apply {
            inputHorario.text?.clear()
            inputNomeTurma.text?.clear()
            inputNomeProfessor.text?.clear()
        }
    }

    fun validateInput(): Boolean {

        // TODO

        return true
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}