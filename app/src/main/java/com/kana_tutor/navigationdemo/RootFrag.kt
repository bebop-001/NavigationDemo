/*
 *  Copyright 2019 sjs@kana-tutor.com
 *
 *  Permission is hereby granted, free of charge, to any person
 *  obtaining 2019 sjs@kana-tutor.com copy of this software and
 *  associated documentation files (the "Software"), to deal in the
 *  Software without restriction, including without limitation the
 *  rights to use, copy, modify, merge, publish, distribute,
 *  sublicense, and/or sell copies of the Software, and to permit
 *  persons to whom the Software is furnished to do so, subject to
 *  the following conditions:
 *
 *  The above copyright notice and this permission notice shall be
 *  included in all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 *  OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *  NON-INFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 *  HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 *  WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 *  FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 *  OTHER DEALINGS IN THE SOFTWARE.
 *
 */

package com.kana_tutor.navigationdemo

import android.os.Bundle
import android.view.*
import android.view.Menu
import android.view.MenuItem
import android.view.MenuInflater
import android.widget.Toast

import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController

import com.kana_tutor.navigationdemo.databinding.RootFragBinding

/*
 * A simple fragment with a button or two to demonstrate data-binding,
 * menues from a fragment and instantiation of a fragment using
 * the new Navigate stuff.
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
            else -> rv = super.onOptionsItemSelected(item)
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
        // button onClick event listeners.
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
