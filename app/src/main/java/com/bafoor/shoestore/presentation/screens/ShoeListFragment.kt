package com.bafoor.shoestore.presentation.screens

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.bafoor.shoestore.R
import com.bafoor.shoestore.databinding.FragmentShoeListBinding
import com.bafoor.shoestore.databinding.ShoeListBinding
import com.bafoor.shoestore.presentation.ShoeListViewModel


class ShoeListFragment : Fragment() {
    private lateinit var binding: FragmentShoeListBinding
    private val viewModel: ShoeListViewModel by activityViewModels()
    private lateinit var mLinearLayout: ViewGroup
    private lateinit var newLayout: ShoeListBinding
    //private val args : ShoeListFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_list, container, false
        )
        //adding menu

        val view = binding.root



        mLinearLayout = binding.shoeRoot
        val list = viewModel.shoeList.value
        if (list != null) {
            for (items in list){
                newLayout =
                    DataBindingUtil.inflate(
                        inflater, R.layout.shoe_list, container,false
                    )
                addViewGroup(
                    items.company,
                    items.shoesName,
                    items.shoesSize.toString(),
                    items.description
                )
            }
        }

        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object: MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.log_out_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when(menuItem.itemId){
                    R.id.logInFragment ->{
                        findNavController().navigate(
                            R.id.action_shoeListFragment_to_logInFragment
                        )
                        true
                    }
                    else -> false
                }
            }
        },viewLifecycleOwner, Lifecycle.State.RESUMED)


        binding.addFragmentBtn.setOnClickListener {
            val action = ShoeListFragmentDirections.actionShoeListFragmentToAddFragment()
            findNavController().navigate(action)
        }
        backButtonPressed()
        return view
    }



    private fun addViewGroup(
        shoeCompany: String,
        shoeName: String,
        shoeSize: String,
        shoeDesc: String
    ) {


        val newCompanyName: TextView = newLayout.shoeCompany
        val newShoeName: TextView = newLayout.shoeName
        val newShoeSize: TextView = newLayout.shoeSize
        val newDesc: TextView = newLayout.shoeDesc

        newCompanyName.text = shoeCompany
        newShoeName.text = shoeName
        newShoeSize.text = shoeSize
        newDesc.text = shoeDesc
        binding.shoeRoot.addView(newLayout.root)
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