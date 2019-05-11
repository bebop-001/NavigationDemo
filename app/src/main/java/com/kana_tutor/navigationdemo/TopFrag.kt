package com.kana_tutor.navigationdemo


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.kana_tutor.navigationdemo.databinding.TopFragBinding

/**
 * A simple [Fragment] subclass.
 *
 */
class TopFrag : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil
            .inflate<com.kana_tutor.navigationdemo.databinding.TopFragBinding>(
                inflater, R.layout.top_frag
                , container, false
            )
        binding.toRootFromTopBtn.setOnClickListener {view:View ->
            view.findNavController().navigate(
                R.id.action_topFrag_to_rootFrag
            )
        }
        return binding.root
    }
}
