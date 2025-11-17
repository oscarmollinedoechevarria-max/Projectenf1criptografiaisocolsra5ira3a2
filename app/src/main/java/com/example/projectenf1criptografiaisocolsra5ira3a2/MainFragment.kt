package com.example.projectenf1criptografiaisocolsra5ira3a2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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

        val toolbar = view?.findViewById<MaterialToolbar>(R.id.topAppBar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        toolbar?.title = "Validador de codi"
        toolbar?.setTitleTextColor(ContextCompat.getColor(requireContext(), android.R.color.black))


        binding.button.setOnClickListener {
            try {
                val text = binding.editTextText.text.toString()

                val mSocket: Socket = IO.socket("http://192.168.19.215:3000")

                mSocket.connect()

                mSocket.emit("registerPlatform", "mobile");

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