package com.rafaelleal.android.turmasdatabaseproject.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.rafaelleal.android.turmasdatabaseproject.Fragments.adapters.AlunoListener
import com.rafaelleal.android.turmasdatabaseproject.Fragments.adapters.AlunosAdapter
import com.rafaelleal.android.turmasdatabaseproject.R
import com.rafaelleal.android.turmasdatabaseproject.databinding.FragmentAlunosBinding
import com.rafaelleal.android.turmasdatabaseproject.models.Aluno
import com.rafaelleal.android.turmasdatabaseproject.models.Turma
import com.rafaelleal.android.turmasdatabaseproject.utils.nav
import com.rafaelleal.android.turmasdatabaseproject.utils.toast
import com.rafaelleal.android.turmasdatabaseproject.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class AlunosFragment : Fragment() {


    val TAG = "Escola"

    val viewModel: MainViewModel by activityViewModels()

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // ViewBinding     /////////////////////////////////////////////////////////////////////////////
    // https://developer.android.com/topic/libraries/view-binding?hl=pt-br#fragments  //////////////
    private var _binding: FragmentAlunosBinding? = null

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
        _binding = FragmentAlunosBinding.inflate(inflater, container, false)
        val view = binding.root

        setup()

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                // Use Collect para receber um StateFlow
                // import kotlinx.coroutines.flow.collect
                viewModel.alunos.collect { alunos ->
                    adapter.submitList(alunos)
                    binding.rvAlunos.adapter = adapter
//                    Log.i(TAG, alunos.toString())
//                    alunos.forEach {
//                        Log.i(TAG, it.nome)
//                    }
                }
            }
        }
    }


    val adapter = AlunosAdapter(
        object : AlunoListener {
            override fun onEditClick(aluno: Aluno) {
                //TODO("Not yet implemented")
                toast("Editar")
            }

            override fun onDeleteClick(aluno: Aluno) {
                //TODO("Not yet implemented")
                toast("Apagar")
                viewModel.deleteAluno(aluno)
            }

        }
    )

    private fun setup() {
        setupViews()
        setupClickListeners()
        setupRecyclerView()
    }

    private fun setupViews() {
        getActivity()?.setTitle("Alunos");
    }


    private fun setupClickListeners() {
        binding.fabAdd.setOnClickListener {
            nav(R.id.action_alunosFragment_to_novoAlunoFragment)
        }
    }

    private fun setupRecyclerView() {
        // adapter precisa ser uma variável global para ser acessada por todos os métodos
        binding.rvAlunos.adapter = adapter
        binding.rvAlunos.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        //viewModel.fetchTurmas()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}