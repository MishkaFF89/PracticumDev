package com.github.mishkaff89.practicumdev.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mishkaff89.practicumdev.Auth
import com.github.mishkaff89.practicumdev.databinding.FragmentAuthBinding
import com.jakewharton.rxbinding4.widget.textChanges


class AuthFragment : Fragment() {
    private lateinit var binding: FragmentAuthBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAuthBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(EMAIL_STRING, binding.etEmail.text.toString())
        outState.putString(PASSWORD_STRING, binding.etPass.text.toString())
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        binding.etEmail.setText(savedInstanceState?.getString(EMAIL_STRING))
        binding.etPass.setText(savedInstanceState?.getString(PASSWORD_STRING))
    }

    private fun init() = with(binding){
        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        etPass.textChanges().subscribe{
            onTextChanged()
        }

        etEmail.textChanges().subscribe {
            onTextChanged()
        }

        btnSignIn.setOnClickListener {
            (requireActivity() as Auth).onAuthSuccess()
        }
    }

    private fun onTextChanged() = with(binding){
        btnSignIn.isEnabled = etEmail.text.length >=6 && etPass.text.length >=6
    }

    companion object {
        private const val EMAIL_STRING = "EMAIL_STRING"
        private const val PASSWORD_STRING = "PASSWORD_STRING"
    }

}