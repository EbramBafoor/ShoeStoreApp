package com.bafoor.shoestore.presentation.screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bafoor.shoestore.R
import com.bafoor.shoestore.databinding.FragmentWelcomeBinding



class WelcomeFragment : Fragment() {
    private val args : WelcomeFragmentArgs by navArgs()
    private lateinit var binding: FragmentWelcomeBinding
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_welcome, container, false
        )
        val view = binding.root
        welcomeFragmentVisiting()
        binding.welcomeTv.text = "Welcome ${args.name}"

        // navigate to the instructions screen
        binding.instructionBtn.setOnClickListener {
            val action = WelcomeFragmentDirections.
            actionWelcomeFragment2ToInstructionsFragment()
            findNavController().navigate(action)
        }


        backButtonPressed()
        return view
    }

    @SuppressLint("CommitPrefEdits")
    private fun welcomeFragmentVisiting(){
        val sharedPreference = requireActivity().getSharedPreferences(
            "welcomeFragment",Context.MODE_PRIVATE
        )
        val editor = sharedPreference.edit()
        editor.putBoolean("Visited", true)
        editor.apply()
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