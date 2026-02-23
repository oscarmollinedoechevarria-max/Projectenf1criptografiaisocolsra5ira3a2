package com.example.projectenf1criptografiaisocolsra5ira3a2.report

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.projectenf1criptografiaisocolsra5ira3a2.R
import com.example.projectenf1criptografiaisocolsra5ira3a2.databinding.FragmentReportProblemBinding

class ReportProblemFragment : Fragment() {

    private val viewModel: ReportProblemViewModel by viewModels()

    lateinit var binding: FragmentReportProblemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReportProblemBinding.inflate(inflater)

        setupButtons()

        return binding.root
    }

    fun setupButtons(){
        binding.buttonEnviar.setOnClickListener {
            val textProblem = binding.editTextProblema

            if (!textProblem.text.isBlank()) {
                val problema = textProblem.text.toString()

                viewModel.enviarProblema(requireContext(), problema)

                Toast.makeText(requireContext(), "Enviat correctament", Toast.LENGTH_SHORT).show()

                findNavController().navigate(R.id.action_reportProblemFragment_to_mainFragment4)
            }
            else {
                textProblem.error = "Camp buit"
                 Toast.makeText(requireContext(), "Camp buit, emplena el camp per enviar", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonTornar.setOnClickListener {
            findNavController().navigate(R.id.action_reportProblemFragment_to_mainFragment4)
        }
    }}