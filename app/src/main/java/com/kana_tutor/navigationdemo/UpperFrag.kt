package com.kana_tutor.navigationdemo

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.kana_tutor.navigationdemo.databinding.UpperFragBinding

/**
 * A simple [Fragment] subclass.
 *
 */
class UpperFrag() : Fragment() {

    // Menu item selected listener.
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var rv = true
        when (item.itemId) {
            R.id.i_am_upper_frag_item ->
                Toast.makeText(
                this.context, item.title, Toast.LENGTH_LONG).show()
            // If item isn't for this menu, you must call the super or
            // other things that must happen (eg: up-button ins
            // onSupportNavigateUp) won't happen.
            else -> rv = super.onOptionsItemSelected(item);
        }
        return rv
    }

    // Over-ride the default onCreateOptionsMenu  callback
    // to inflate our app bar overflow menu.
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_upper_frag, menu)
    }

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
        setHasOptionsMenu(true)
        return binding.root
    }
}

