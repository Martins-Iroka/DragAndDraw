package com.martdev.android.draganddrawkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class DragAndDrawActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val manager = supportFragmentManager
        val fragment = DragAndDrawFragment.newInstance()
        manager.beginTransaction().add(R.id.fragment_container, fragment).commit()
    }
}
