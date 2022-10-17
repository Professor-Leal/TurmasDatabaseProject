package com.rafaelleal.android.turmasdatabaseproject.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.rafaelleal.android.turmasdatabaseproject.R
import com.rafaelleal.android.turmasdatabaseproject.databinding.FragmentNovaTurmaBinding
import com.rafaelleal.android.turmasdatabaseproject.models.Turma
import com.rafaelleal.android.turmasdatabaseproject.utils.navUp
import com.rafaelleal.android.turmasdatabaseproject.viewmodel.MainViewModel

class NovaTurmaFragment : Fragment() {


    val TAG = "Escola"

    val viewModel: MainViewModel by activityViewModels()


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // ViewBinding     /////////////////////////////////////////////////////////////////////////////
    // https://developer.android.com/topic/libraries/view-binding?hl=pt-br#fragments  //////////////
    private var _binding: FragmentNovaTurmaBinding? = null

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
        _binding = FragmentNovaTurmaBinding.inflate(inflater, container, false)
        val view = binding.root

        setup()

        return view
    }

    private fun setup() {
        setupViews()
        setupClickListeners()
    }

    private fun setupViews() {
        getActivity()?.setTitle("Nova Turma");
    }

    private fun setupClickListeners() {
        binding.apply {
            fabAddTurma.setOnClickListener {
                addNovaTurma()
                navUp()
            }
        }
    }

    fun addNovaTurma() {
        if (validateInput()) {
            val novaTurma = getNovaTurmaFromInputs()
            viewModel.insertTurma(novaTurma)
        }
    }

    fun getNovaTurmaFromInputs(): Turma {
        return Turma(
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