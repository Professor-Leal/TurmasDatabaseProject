package com.rafaelleal.android.turmasdatabaseproject.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rafaelleal.android.turmasdatabaseproject.R
import com.rafaelleal.android.turmasdatabaseproject.databinding.FragmentAlunosBinding

class AlunosFragment : Fragment() {


    val TAG = "Escola"


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

    private fun setup() {
        setupViews()
    }

    private fun setupViews() {
        getActivity()?.setTitle("Alunos");
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}