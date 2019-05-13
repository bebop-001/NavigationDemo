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

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.util.TypedValue
import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import android.view.Menu
import android.view.MenuItem
import android.view.MenuInflater

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI

// this file was created from the <layout> tag in activity_main.xml.
// Note that ActivityMainBinding is a mangled version of activity_main.
import com.kana_tutor.navigationdemo.databinding.ActivityMainBinding
import java.io.File
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {
    @SuppressLint("UNUSED")
    private val logTag = "MainActivity"

    // Return a spanned html string using the appropriate call for
    // the user's device.
    private fun htmlString(htmlString:String) : Spanned {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(htmlString, HtmlCompat.FROM_HTML_MODE_LEGACY)
        }
        else {
            @Suppress("DEPRECATION")
            Html.fromHtml(htmlString)
        }
    }
    // Display info about the build using an AlertDialog.
    private fun displayAboutInfo() : Boolean {
        val appInfo = packageManager
            .getApplicationInfo(BuildConfig.APPLICATION_ID, 0)
        val installTimestamp = File(appInfo.sourceDir).lastModified()

        val htmlString = String.format(getString(R.string.about_query)
            , getString(R.string.app_name)
            , BuildConfig.VERSION_CODE, BuildConfig.VERSION_NAME
            , SimpleDateFormat.getInstance().format(
                java.util.Date(BuildConfig.BUILD_TIMESTAMP))
            , SimpleDateFormat.getInstance().format(
                java.util.Date(installTimestamp))
            , if(BuildConfig.DEBUG) "debug" else "release"
            , BuildConfig.BRANCH_NAME
        )

        val aboutTv = TextView(this)
        aboutTv.apply {
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 20.0f)
            setTypeface(null, Typeface.BOLD)
            text = htmlString(htmlString)
            gravity = Gravity.CENTER
        }

        AlertDialog.Builder(this)
            .setView(aboutTv)
            .show()
        return true
    }
    // Menu item selected listener.
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var rv = true
        when (item.itemId) {
            R.id.about_item -> displayAboutInfo()
            // If item isn't for this menu, you must call the super or
            // other things that must happen (eg: up-button in onSupportNavigateUp)
            // won't happen.
            else -> rv = super.onOptionsItemSelected(item);        }
        return rv
    }

    // Over-ride the default onCreateOptionsMenu  callback
    // to inflate our app bar overflow menu.
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return true
    }

    // this is the kotlin equivalent of a static variable.
    // It's here so when MainActivity is re-created during an
    // orientation change, the counter isn't re-initialized.
    private companion object {
        var counter:Int = 0
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
                val nc = this@MainActivity.findNavController(R.id.main_nav_host_frag)
                nc.navigate(R.id.counterInfoFrag)
            }
        }
        // enable the action-bar back arrow.
        NavigationUI.setupActionBarWithNavController(
            this,
            this.findNavController(R.id.main_nav_host_frag)
        )
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.main_nav_host_frag)
        return navController.navigateUp()
    }
}