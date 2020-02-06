package com.example.workman


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * 출퇴근 상세보기 화면
 */
class TimeDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_time_detail, container, false)

        // 출퇴근 기록 수정 요청 버튼 선언
        val request = rootView.findViewById<FloatingActionButton>(R.id.floatingActionButton2)

        // 출퇴근 기록 수정 요청 버튼 클릭 이벤트
        request.setOnClickListener {
            // 출퇴근 기록 수정 요청 화면으로 이동
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.container, TimeRequestFragment()).addToBackStack(null).commit()
        }

        return rootView
    }


}
