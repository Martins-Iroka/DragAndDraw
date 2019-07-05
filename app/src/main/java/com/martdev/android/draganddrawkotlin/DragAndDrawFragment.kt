package com.martdev.android.draganddrawkotlin

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class DragAndDrawFragment : Fragment() {

    companion object {

        fun newInstance(): DragAndDrawFragment {
            return DragAndDrawFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.drag_and_draw, container, false)
    }
}