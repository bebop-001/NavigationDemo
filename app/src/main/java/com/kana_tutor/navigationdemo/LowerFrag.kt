package com.kana_tutor.navigationdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.kana_tutor.navigationdemo.databinding.LowerFragBinding

/**
 * A simple [Fragment] subclass.
 *
 */
class LowerFrag : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil
            .inflate<LowerFragBinding>(
                inflater, R.layout.lower_frag
                , container, false
            )
        binding.toTopFromLowerBtn.setOnClickListener { view:View ->
            view.findNavController().navigate(
                R.id.action_lowerFrag_to_topFrag
            )
        }
        return binding.root
    }
}
