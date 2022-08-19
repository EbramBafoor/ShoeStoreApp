package com.bafoor.shoestore.presentation.screens

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.bafoor.shoestore.R
import com.bafoor.shoestore.databinding.FragmentLoginBinding


class LogInFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false
        )
        val view = binding.root


        binding.logInBtn.setOnClickListener {
            logInLogic()
        }

        if (welcomeFragmentVisited()){
            binding.alreadyLoggedInBtn.isEnabled = true
            binding.alreadyLoggedInBtn.setOnClickListener {
                val action = LogInFragmentDirections.actionLogInFragmentToInstructionsFragment()
                findNavController().navigate(action)

            }
        }
        backButtonPressed()
        return view
    }

    private fun logInLogic(

    ) {
        val name = binding.nameTv.text.toString()
        val email = binding.emailTv.text.toString()
        val password = binding.passwordTv.text.toString()
        if (
            name.isNotBlank()
            && email.isNotBlank()
            &&  password.isNotBlank()
            && !Patterns.EMAIL_ADDRESS.matcher(email).matches()
        ) {
            val action = LogInFragmentDirections.actionLogInFragmentToWelcomeFragment2(name)
            findNavController().navigate(action)
        }
        else{
            Toast.makeText(activity,"fill in all fields",Toast.LENGTH_LONG).show()
        }
    }

    private fun welcomeFragmentVisited(): Boolean{
        val sharedPref = requireActivity()
            .getSharedPreferences("welcomeFragment",Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Visited", false)
    }

    private fun backButtonPressed() {
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val intent = Intent()
                intent.action = Intent.ACTION_MAIN
                intent.addCategory(Intent.CATEGORY_HOME)
                startActivity(intent)
            }
        })
    }

}