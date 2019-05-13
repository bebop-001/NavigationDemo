package com.kana_tutor.navigationdemo

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.kana_tutor.navigationdemo.databinding.LowerFragBinding

/**
 * A simple [Fragment] subclass.
 *
 */
class LowerFrag : Fragment() {
    // Menu item selected listener.
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var rv = true
        when (item.itemId) {
            R.id.i_am_lower_frag_item ->
                Toast.makeText(this.context, item.title, Toast.LENGTH_LONG).show()
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
        inflater.inflate(R.menu.menu_lower_frag, menu)
    }

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
        setHasOptionsMenu(true)
        return binding.root
    }
}
