package com.udacity.shoestore.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.InverseBindingAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailsBinding
import com.udacity.shoestore.databinding.FragmentShoeListBinding

import com.udacity.shoestore.models.Shoe


class ShoeDetails : Fragment() {
    private val sharedViewModel: ShoeListViewModel by activityViewModels()
    private lateinit var binding: FragmentShoeDetailsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_details, container, false
        )
        sharedViewModel.eventSave.observe(viewLifecycleOwner, Observer { isSaving ->
            if (isSaving) {
                onSaving()
            }
        })
        binding.cancelButton.setOnClickListener { view: View ->
            sharedViewModel.savedShoe()
            view.findNavController().navigateUp()
        }
        return binding.root
    }
//    ACTIONS FOR ADDING AND SAVING A SHOE TO THE LIST
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            shoeListViewModel = sharedViewModel
            shoe = Shoe("", 0.0, "", "")
        }
    }
    fun onSaving() {
            NavHostFragment.findNavController(this)
                .navigate(ShoeDetailsDirections.actionShoeDetailsToShoeListFragment2())
        sharedViewModel.savedShoe()
    }

}

@BindingAdapter("android:text")
fun bindDoubleInText(tv: EditText, value: Double) {
    tv.setText(value.toString())
    tv.text?.let {
        tv.setSelection(it.length)
    }
}

@InverseBindingAdapter(attribute = "android:text")
fun getDoubleFromBinding(view: TextView): Double {
    val string = view.text.toString()
    return if (string.isEmpty()) 0.0 else string.toDouble()
}
