package com.example.workman


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * A simple [Fragment] subclass.
 */
class TimeDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_time_detail, container, false)

        val btnRequest = rootView.findViewById<FloatingActionButton>(R.id.floatingActionButton2)

        btnRequest.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.container, TimeRequestFragment()).addToBackStack(null).commit()
        }

        return rootView
    }


}
