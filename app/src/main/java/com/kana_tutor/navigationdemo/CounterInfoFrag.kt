package com.kana_tutor.navigationdemo


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import com.kana_tutor.navigationdemo.databinding.CounterInfoFragBinding

/**
 * A simple [Fragment] subclass.
 *
 */
class CounterInfoFrag : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil
            .inflate<CounterInfoFragBinding>(
                inflater, R.layout.counter_info_frag
                , container, false
            )
        return binding.root
    }
}
