package com.udacity.shoestore.products

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.FragmentProductItemBinding

class ShoeListFragment : Fragment() {

    private val sharedViewModel: ShoeListViewModel by activityViewModels()
    private lateinit var binding: FragmentShoeListBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_list, container, false
        )

        sharedViewModel.listProducts.observe(viewLifecycleOwner, Observer { products ->
            products.forEach { product ->
                val itemBinding: FragmentProductItemBinding =
                    DataBindingUtil.inflate(inflater, R.layout.fragment_product_item, container, false)
                itemBinding.shoe = product
                binding.productsViewgroup.addView(itemBinding.root)
            }
        })
//
        binding.createShoeButton.setOnClickListener { v: View ->
            v.findNavController()
                .navigate(ShoeListFragmentDirections.actionShoeListFragment2ToShoeDetails())
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
        }
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.logout_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}