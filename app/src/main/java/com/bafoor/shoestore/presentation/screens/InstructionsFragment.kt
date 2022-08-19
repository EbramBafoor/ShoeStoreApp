package com.bafoor.shoestore.presentation.screens

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.bafoor.shoestore.R

import com.bafoor.shoestore.databinding.FragmentInstructionsBinding




class InstructionsFragment : Fragment() {
    private lateinit var binding: FragmentInstructionsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_instructions, container, false
        )
        val view = binding.root

        binding.shoeListBtn.setOnClickListener {
            val action = InstructionsFragmentDirections
                .actionInstructionsFragmentToShoeListFragment()
            findNavController().navigate(action)
        }
        backButtonPressed()

        return view
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