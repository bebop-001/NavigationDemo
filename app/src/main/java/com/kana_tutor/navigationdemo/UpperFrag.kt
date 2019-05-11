package com.kana_tutor.navigationdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.kana_tutor.navigationdemo.databinding.UpperFragBinding

/**
 * A simple [Fragment] subclass.
 *
 */
class UpperFrag : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil
            .inflate<UpperFragBinding>(
                inflater, R.layout.upper_frag
                , container, false
            )
        binding.toTopFromUpperBtn.setOnClickListener { view : View ->
            view.findNavController().navigate(
                R.id.action_upperFrag_to_topFrag
            )
        }
        return binding.root
    }
}

