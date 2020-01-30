package com.example.workman
import android.graphics.Color

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Button
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

/**
 * 홈
 */

class HomeFragment : Fragment(), OnMapReadyCallback{

    private lateinit var mapView: MapView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        // 툴바 타이틀 변경하기
//        activity!!.toolbar.title = "홈"
//        activity!!.toolbar.setTitleTextColor(Color.WHITE)

        val rootView = inflater.inflate(R.layout.fragment_home, container, false)

        val invite = rootView.findViewById<Button>(R.id.button)

        // 초대하기 버튼 클릭
        invite.setOnClickListener {
            // 직원 초대하는 화면으로 이동
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.container, InviteFragment()).addToBackStack(null).commit()

        }

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mapView = view!!.findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap) {

        val seoul = LatLng(37.566, 126.978)
        p0.moveCamera(CameraUpdateFactory.newLatLng(seoul))
        p0.moveCamera(CameraUpdateFactory.zoomTo(10f))

        val marker =
            MarkerOptions()
                .position(seoul)
                .title("서울")
                .snippet("아름다운 도시")
        p0.addMarker(marker)

    }

    override fun onStart() {
        mapView.onStart()
        super.onStart()
    }

    override fun onResume() {
        mapView.onResume()
        super.onResume()
    }

    override fun onPause() {
        mapView.onPause()
        super.onPause()
    }

    override fun onStop() {
        mapView.onStop()
        super.onStop()
    }

    override fun onDestroy() {
        mapView.onDestroy()
        super.onDestroy()
    }

    override fun onLowMemory() {
        mapView.onLowMemory()
        super.onLowMemory()
    }

}
