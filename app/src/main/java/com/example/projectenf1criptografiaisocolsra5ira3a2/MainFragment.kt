package com.example.projectenf1criptografiaisocolsra5ira3a2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.projectenf1criptografiaisocolsra5ira3a2.databinding.FragmentMainBinding
import com.google.android.material.appbar.MaterialToolbar
import io.socket.client.IO;
import io.socket.client.Socket;
import org.json.JSONObject

class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMainBinding.inflate(inflater)

        binding.button2.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment4_to_reportProblemFragment)
        }

        val mSocket: Socket = IO.socket("http://192.168.18.183:3000")

        mSocket.connect()

        mSocket.emit("registerPlatform", "mobile");


        binding.button.setOnClickListener {
            try {
                val text = binding.editTextText.text.toString()
                val json = JSONObject()
                json.put("codigo", text)

                mSocket.emit("validarCodigo", json)

                Toast.makeText(context, "codi enviat", Toast.LENGTH_SHORT).show()

            }catch (e: Exception){
                Toast.makeText(context, "error al enviar el codi", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }
}