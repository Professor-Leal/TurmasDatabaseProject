package com.rafaelleal.android.turmasdatabaseproject.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.rafaelleal.android.turmasdatabaseproject.R
import com.rafaelleal.android.turmasdatabaseproject.databinding.FragmentNovoAlunoBinding
import com.rafaelleal.android.turmasdatabaseproject.models.Aluno
import com.rafaelleal.android.turmasdatabaseproject.utils.navUp
import com.rafaelleal.android.turmasdatabaseproject.viewmodel.MainViewModel

class NovoAlunoFragment : Fragment() {


    val TAG = "Escola"

    val viewModel: MainViewModel by activityViewModels()


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // ViewBinding     /////////////////////////////////////////////////////////////////////////////
    // https://developer.android.com/topic/libraries/view-binding?hl=pt-br#fragments  //////////////
    private var _binding: FragmentNovoAlunoBinding? = null

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
        _binding = FragmentNovoAlunoBinding.inflate(inflater, container, false)
        val view = binding.root

        setup()

        return view
    }

    private fun setup() {
        setupViews()
        setupClickListeners()
    }

    private fun setupViews() {
        getActivity()?.setTitle("Novo Aluno");
    }

    private fun setupClickListeners() {
        binding.apply {
            fabAddAluno.setOnClickListener {
                addNovoAluno()
                navUp()
            }
        }
    }

    fun addNovoAluno() {
        if (validateInput()) {
            val novoAluno = getNovoAlunoFromInputs()
            Log.i(TAG, "Aluno: ${novoAluno.nome}")
            Log.i(TAG, "matricula: ${novoAluno.matricula}")
            Log.i(TAG, "periodo: ${novoAluno.periodo}")
            viewModel.insertAluno(novoAluno)
        }
    }

    fun getNovoAlunoFromInputs(): Aluno {
        return Aluno(
            nome = binding.inputNomeAluno.text.toString(),
            matricula = binding.inputMatricula.text.toString(),
            periodo = binding.inputPeriodo.text.toString().toInt()
        )
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