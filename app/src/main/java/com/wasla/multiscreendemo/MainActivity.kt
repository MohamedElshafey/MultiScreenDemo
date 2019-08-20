package com.wasla.multiscreendemo

import android.content.Context
import android.hardware.display.DisplayManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Display
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var displayManager: DisplayManager? = null
    var presentationDisplays: Array<Display>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun afterTextChanged(s: Editable?) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Observable.updateObserver(s.toString())
            }

        })
        displayManager = getSystemService(Context.DISPLAY_SERVICE) as DisplayManager
        if (displayManager != null) {
            presentationDisplays = displayManager?.getDisplays(DisplayManager.DISPLAY_CATEGORY_PRESENTATION)
            Log.d("PRESENTATION_DISPLAY", " Presentation: " + presentationDisplays?.size)
            if (presentationDisplays?.size ?: 0 > 0) {
//                val secondaryDisplay = SecondaryDisplayVideo(this@MainActivity, presentationDisplays!![0])
                val secondaryDisplay = SecondaryDisplay(this@MainActivity, presentationDisplays!![0])
                secondaryDisplay.show()
            }
        }
    }
}
