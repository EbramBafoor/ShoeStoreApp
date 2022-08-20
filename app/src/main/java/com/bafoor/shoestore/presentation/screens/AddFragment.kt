package com.bafoor.shoestore.presentation.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels

import androidx.navigation.fragment.findNavController
import com.bafoor.shoestore.R
import com.bafoor.shoestore.data.Shoe
import com.bafoor.shoestore.databinding.FragmentAddBinding
import com.bafoor.shoestore.presentation.ShoeListViewModel
import com.bafoor.shoestore.presentation.ShoeStoreEvent


class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private val viewModels: ShoeListViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_add, container, false
        )
        val view = binding.root


        binding.addBtn.setOnClickListener {
            val shoeName: String = binding.nameTv.text.toString()
            val shoeSize: String = binding.sizeTv.text.toString()
            val shoeCompany: String = binding.companyTv.text.toString()
            val desc: String = binding.descTv.text.toString()
            if (shoeName.isNotBlank() &&
                shoeSize.isNotBlank() &&
                shoeCompany.isNotBlank() &&
                desc.isNotBlank()
            ) {
                viewModels.onEvent(
                    ShoeStoreEvent.AddShoe(
                    Shoe(
                        shoeName, shoeCompany, shoeSize, desc
                    )
                ))
                val action = AddFragmentDirections.actionAddFragmentToShoeListFragment()
                findNavController().navigate(action)
            }
        }
        return view
    }
}