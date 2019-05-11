package com.kana_tutor.navigationdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
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
        return binding.root
    }
}
