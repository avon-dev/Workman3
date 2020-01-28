package com.example.workman

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_time.*


/**
 * 출퇴근
 */
class TimeFragment : Fragment() {

    private var timeList = arrayListOf<Time>(

        Time(1, "월요일", 11, 12),
        Time(2, "화요일", 14, 17),
        Time(3, "수요일", 14, 26),
        Time(4, "목요일", 14, 27),
        Time(5, "금요일", 14, 33),
        Time(6, "토요일", 14, 14),
        Time(7, "일요일", 14, 4),
        Time(8, "월요일", 14, 36)


        )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 툴바 타이틀 변경하기
        activity!!.toolbar.title = "출퇴근"
        activity!!.toolbar.setTitleTextColor(Color.WHITE)

        // Inflate the layout for this fragment
        val rootView =  inflater.inflate(R.layout.fragment_time, container, false)

        val btnRequest = rootView.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        btnRequest.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.container, TimeRequestFragment()).addToBackStack(null).commit()
        }

        val mRecyclerView = rootView.findViewById(R.id.recyclerView) as RecyclerView
        val mAdapter = TimeRvAdapter(context!!, timeList) {
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.container, TimeDetailFragment()).addToBackStack(null).commit()

        }
        mRecyclerView.adapter = mAdapter
        val lm = LinearLayoutManager(context)
        mRecyclerView.layoutManager = lm
        mRecyclerView.setHasFixedSize(true)

        return rootView
    }


}
