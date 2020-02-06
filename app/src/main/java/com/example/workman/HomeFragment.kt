package com.example.workman

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


/**
 * 홈
 */

class HomeFragment : Fragment(), OnMapReadyCallback{

    private lateinit var mapView: MapView // 구글 맵 뷰 위젯

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_home, container, false)

        // 초대하기 버튼 선언
        val invite = rootView.findViewById<Button>(R.id.button)

        // 초대하기 버튼 클릭 이벤트
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

    override fun onMapReady(googleMap: GoogleMap) {

        /**
         *
         */

        googleMap.moveCamera(CameraUpdateFactory.zoomTo(10f))
        googleMap.isMyLocationEnabled = true

        onAddMarker(googleMap)

    }

    // 지정된 위치 반경 원 표시
    private fun onAddMarker(googleMap: GoogleMap) {

        //위도 경도
        val mLatitude = 37.5197889   //위도
        val mLongitude = 126.9403083  //경도

        val position =  LatLng(mLatitude, mLongitude)
        val myMarker = MarkerOptions().position(position)

        val circleOptions = CircleOptions()
        circleOptions.center(position)
            .radius(1000.0)
            .strokeWidth(0f)
            .fillColor(Color.parseColor("#880000ff"))

        googleMap.addMarker(myMarker)
        googleMap.addCircle(circleOptions)

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
