package com.example.burnyourburnout.presentation.lobby

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.burnyourburnout.databinding.FragmentLobbyBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LobbyFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: FragmentLobbyBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLobbyBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initViews() = with(binding) {
        auth = Firebase.auth

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(requireContext(), "환영합니다!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(requireContext(), "로그인에 실패했습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
        }

    }
}