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

import java.text.SimpleDateFormat
import java.util.*

import android.os.Bundle
import android.view.*

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
class CounterInfoFrag : Fragment() {
    // Keep the text here so it will survive re-instantiation of the fragment.
    private companion object {
        var text = ""
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
            text = String.format("%2d)\t\t%s\n", counter, timeStamp) + text
        }
        binding.counterInfoTv.text = text
        return binding.root
    }
}
