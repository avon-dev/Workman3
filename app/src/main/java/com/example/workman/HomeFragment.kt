package com.example.workman


import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 홈
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        // 툴바 타이틀 변경하기
        activity!!.toolbar.title = "Workman"
        activity!!.toolbar.setTitleTextColor(Color.WHITE)


        return inflater.inflate(R.layout.fragment_home, container, false)
    }


}
