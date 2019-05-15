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

import android.content.Intent
import java.text.SimpleDateFormat
import java.util.*

import android.os.Bundle
import android.view.*
import android.widget.Toast

import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil

import com.kana_tutor.navigationdemo.databinding.CounterInfoFragBinding

/*
 * This frag is part of the demo for the safe-args.  It's defined in the
 * navigation editor as expecting two arguments: a long timestamp and an
 * int counter.  It converts the timestamp ito a string date and displays
 * this and the counter in a scrolled text view.
 *
 * Possibly of interest is that I used a scroll view instead of a scrolling
 * text view because the scrolled text view doesn't seem to work in a fragment.
 */
class CounterInfoFrag : Fragment() {    // Menu item selected listener.
    // Keep the text here so it will survive re-instantiation of the fragment.
    private companion object {
        var counterInfoHistory = ""
    }

    // Creating a shared intent to open installed applications with
    private fun getShareIntent( text : String) : Intent ?{
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
            .putExtra(Intent.EXTRA_TEXT, text)
        val destinationExist =
            intent.resolveActivity(this.activity!!.packageManager) != null
        return if(destinationExist) intent else null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var rv = true
        when (item.itemId) {
            R.id.i_am_counter_frag_item ->
                Toast.makeText(this.context, item.title, Toast.LENGTH_LONG).show()
            R.id.share_menu_item -> {
                // we shouldn't be able to get here because we checked
                // when inflating the menu that a destination exists.
                // however -- to be safe, check for null.
                val intent  = getShareIntent(counterInfoHistory)
                if (intent != null)
                    startActivity(intent)
                else
                    Toast.makeText(
                        this.context, "Share FAILED", Toast.LENGTH_LONG
                    ).show()
            }
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
        inflater.inflate(R.menu.menu_counter_info_frag, menu)
        // check to see if a destination exists for our intent
        // (i.e. getShareIntent does not return a null if dest exists)
        // and enable/disable the menu item accordingly.
        val shareItem = menu.findItem(R.id.share_menu_item)
        shareItem.isEnabled = getShareIntent("") != null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil
            .inflate<CounterInfoFragBinding>(
                inflater, R.layout.counter_info_frag
                , container, false
            )
        /* the name CounterInfoFragArgs is a mangled version of
         * counter_info_frag.xml.  CounterInfoFragArgs is created by
         * gradle as part of the build process.  The Navigation Editor
         * is used to define the types and names of the variables we
         * expect.  Instantiation with NavController.navigate allows the
         * caller to either pass in a bundle or use a special navigation
         * resource (created only for fragments with a direction) to pass
         * in the new values.
         */
        val args = CounterInfoFragArgs.fromBundle(arguments!!)
        args.apply {
            val timeStamp = SimpleDateFormat(
                "HH:mm:ss M/d/yyyy z"
                , Locale.getDefault()
            )
            .format(changeTimestamp)
            // prepend the new info to the existing info.
            counterInfoHistory =
                "${String.format("%2d)\t\t%s\n", counter, timeStamp)}$counterInfoHistory"
        }
        binding.counterInfoTv.text = counterInfoHistory
        setHasOptionsMenu(true)
        return binding.root
    }
}
