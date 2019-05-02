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
 *  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 *  HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 *  WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 *  FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 *  OTHER DEALINGS IN THE SOFTWARE.
 *
 */

package com.kana_tutor.navigationdemo

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil

// this file was created from the <layout> tag in activity_main.xml.
// Note that ActivityMainBinding is a mangled version of activity_main.
import com.kana_tutor.navigationdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // this is the kotlin equivalent of a static variable.
    // It's here so when MainActivity is re-created during an
    // orientation change, the counter isn't re-initialized.
    companion object {
        private var counter = 0
    }

    // Set the text value with the button text and the counter value.
    @SuppressLint("SetTextI18n")
    fun setText (b : Button, t:TextView) {
        if (counter > 0) {
            // using "property accessor syntax."
            t.text = "${b.text} : $counter"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // the data-binding "magic" happens here.  DataBindingUtil.java
        // defines the various methods for data binding.
        val binding : ActivityMainBinding
                = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            // init the text view.
            // Note that buttonBtn1, textviewTv1 are mangled versions of
            // button_btn_1 and textview_tv_1 used in activity_main.xml.
            setText(buttonBtn1, textviewTv1)
            // define our button callback.
            buttonBtn1.setOnClickListener {
                    counter++
                    setText(buttonBtn1, textviewTv1)
            }
        }
    }
}
