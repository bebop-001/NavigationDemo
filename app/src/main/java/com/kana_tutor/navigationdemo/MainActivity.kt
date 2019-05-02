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
