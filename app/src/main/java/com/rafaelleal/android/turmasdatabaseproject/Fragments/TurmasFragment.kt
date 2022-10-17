package com.rafaelleal.android.turmasdatabaseproject.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.rafaelleal.android.turmasdatabaseproject.Fragments.adapters.TurmaListener
import com.rafaelleal.android.turmasdatabaseproject.Fragments.adapters.TurmasAdapter
import com.rafaelleal.android.turmasdatabaseproject.R
import com.rafaelleal.android.turmasdatabaseproject.databinding.FragmentTurmasBinding
import com.rafaelleal.android.turmasdatabaseproject.utils.nav
import com.rafaelleal.android.turmasdatabaseproject.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect


class TurmasFragment : Fragment() {

    val TAG = "Escola"

    val viewModel: MainViewModel by activityViewModels()

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // ViewBinding     /////////////////////////////////////////////////////////////////////////////
    // https://developer.android.com/topic/libraries/view-binding?hl=pt-br#fragments  //////////////
    private var _binding: FragmentTurmasBinding? = null

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
        _binding = FragmentTurmasBinding.inflate(inflater, container, false)
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
                viewModel.turmas.collect{
                    turmas ->
                    adapter.submitList(turmas)
                    binding.rvTurmas.adapter = adapter
                }

            }
        }



    }

    val adapter = TurmasAdapter(
        object : TurmaListener {
            override fun onClick(posicao: Int) {
                // TODO(evento de clique)
            }

        }
    )

    private fun setup() {
        setupViews()
        setupRecyclerView()
        setupClickListeners()
        setupObservers()
    }

    private fun setupViews() {
        getActivity()?.setTitle("Turmas");
    }

    private fun setupObservers() {
//        viewModel.listaTurmas.observe(viewLifecycleOwner){
//            adapter.submitList(it)
//            binding.rvTurmas.adapter = adapter
//        }
    }

    private fun setupClickListeners() {
        binding.fabAdd.setOnClickListener {
            nav(R.id.action_turmasFragment_to_novaTurmaFragment)
        }
    }

    private fun setupRecyclerView() {
        // adapter precisa ser uma variável global para ser acessada por todos os métodos
        binding.rvTurmas.adapter = adapter
        binding.rvTurmas.layoutManager = LinearLayoutManager(
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