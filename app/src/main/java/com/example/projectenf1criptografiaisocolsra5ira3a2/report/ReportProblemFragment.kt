package com.example.projectenf1criptografiaisocolsra5ira3a2.report

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.projectenf1criptografiaisocolsra5ira3a2.databinding.FragmentReportProblemBinding

class ReportProblemFragment : Fragment() {

    private val viewModel: ReportProblemViewModel by viewModels()

    lateinit var binding: FragmentReportProblemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReportProblemBinding.inflate(inflater)

        setupSendProblemButton()

        return binding.root
    }

    fun setupSendProblemButton(){
        binding.buttonEnviar.setOnClickListener {
            val textProblem = binding.editTextProblema

            if (!textProblem.text.isBlank()) {
                val problema = textProblem.text.toString()

                viewModel.enviarProblema(requireContext(), problema)
            }
            else {
                textProblem.error = "Camp buit"
                 Toast.makeText(requireContext(), "Camp buit, emplena el camp per enviar", Toast.LENGTH_SHORT).show()
            }
        }
    }}