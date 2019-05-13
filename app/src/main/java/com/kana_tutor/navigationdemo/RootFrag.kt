package com.kana_tutor.navigationdemo


import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.Menu
import android.view.MenuItem
import android.view.MenuInflater
import android.widget.Toast

import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.kana_tutor.navigationdemo.databinding.RootFragBinding

/**
 * A simple [Fragment] subclass.
 *
 */
class RootFrag : Fragment() {
    // Menu item selected listener.
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var rv = true
        when (item.itemId) {
            R.id.i_am_root_frag_item ->
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
        inflater.inflate(R.menu.menu_root_frag, menu)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil
            .inflate<RootFragBinding>(
                inflater, R.layout.root_frag
                , container, false
            )
        binding.apply {
            toUpperBtn.setOnClickListener { view: View ->
                view.findNavController().navigate(
                    R.id.action_rootFrag_to_upperFrag
                )
            }
            toLowerBtn.setOnClickListener { view:View ->
                view.findNavController().navigate(
                    R.id.action_rootFrag_to_lowerFrag
                )
            }
        }
        setHasOptionsMenu(true)
        return binding.root
    }
}
