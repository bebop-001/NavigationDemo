package com.kana_tutor.navigationdemo


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.kana_tutor.navigationdemo.databinding.RootFragBinding

/**
 * A simple [Fragment] subclass.
 *
 */
class RootFrag : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil
            .inflate<RootFragBinding>(
                inflater, R.layout.root_frag
                , container, false
            )
        return binding.root
    }
}
