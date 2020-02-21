package com.example.workman.View.Add_Work_Location

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import com.example.workman.Network.Common
import com.example.workman.Network.ServiceApi
import com.example.workman.R
import com.example.workman.View.Select_Company.Select_Company
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_add_work_location.*
import java.io.IOException
import java.util.*


class Add_Work_Location : AppCompatActivity(),
    OnMapReadyCallback {
    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    private var company_name: String? = null // 회사명.
    private var people_num: String? = null   // 회사 인원수.
    private var company_address: String? = null // 회사 주소.
    private var company_code: String? = null //회사 코드.
    private var cp_lat: String? = null // 회사 위도.
    private var cp_lng: String? = null // 회사 경도.
    private var owner_uid = ""     // 회사 최고 관리자 uid
    private var cp_group = ""      // 회사 그룹
    private var certCharLength = 10  // 코드는 문자 10개
    private val characterTable = listOf(    // 코드를 구성하는 문자
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
        'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
        'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
        'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
        'w', 'x', 'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8',
        '9', '0'
    )

    //메모리누수막기
    var compositeDisposable = CompositeDisposable()
    lateinit var serviceapi: ServiceApi

    private lateinit var mMap: GoogleMap
    private var geocoder: Geocoder? = null
    private var ImageButton: ImageButton? = null
    private var editText: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_work_location)
        // 툴바 설정하기
        val toolbar = findViewById<Toolbar>(R.id.add_work_location_toolbar)
        setSupportActionBar(toolbar)
        val ab = supportActionBar!!
        ab.setDisplayShowTitleEnabled(false)
        ab.setDisplayHomeAsUpEnabled(true)  // 뒤로가기 버튼 추가.
        serviceapi= Common.registerapi
        company_name = intent.getStringExtra("company_name")
        people_num = intent.getStringExtra("people_num")
//        Toast.makeText(this,"company_name: ${company_name} + people_num: ${people_num}",Toast.LENGTH_SHORT).show()

        editText = findViewById(R.id.editText2)
        ImageButton = findViewById(R.id.imageButton2)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        var mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapView_add_work_location) as SupportMapFragment?
        mapFragment?.getMapAsync(this)

        // 취소 버튼눌렀을때 지금 화면은 사라지고 회사 선택화면으로 돌아간다.
        button_cancel.setOnClickListener {
            val intent = Intent(this, Select_Company::class.java)
            startActivity(intent)
            finish()
        }

        // 생성하기 버튼을 눌렀을때 회사가 생성이 되고 메인 액티비티 화면으로 이동한다.
        button_create_company.setOnClickListener {
            if (company_address != null) {
                var buf: String? = ""
                for (i in 1..certCharLength) {
                    var random = Random().nextInt(characterTable.size)
                    buf = buf + characterTable[random].toString()
                }
                company_code = buf
                Create_Company(company_name,people_num,cp_lat,cp_lng,company_address,owner_uid,company_code,cp_group)
                Toast.makeText(this, "회사 생성.", Toast.LENGTH_SHORT).show()
//                val intent = Intent(this, Select_Company::class.java)
//                startActivity(intent)
//                finish()
            } else {
                Toast.makeText(this, "회사 주소를 지정해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

        }
    }

    // 툴바 뒤로가기 버튼 설정
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // permission 검사, 요청
    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
            return
        }
    }

    override fun onMapReady(p0: GoogleMap) {
        mMap = p0
        geocoder = Geocoder(this)
        setUpMap()

        // 맵 터치 이벤트 구현 //
        mMap.setOnMapClickListener { point ->
            val mOptions = MarkerOptions()
            // 마커 타이틀
            mOptions.title("마커 좌표")
            val latitude = point.latitude // 위도
            val longitude = point.longitude // 경도
            // 마커의 스니펫(간단한 텍스트) 설정
            mOptions.snippet("$latitude, $longitude")
            // LatLng: 위도 경도 쌍을 나타냄
            mOptions.position(LatLng(latitude, longitude))
            // 마커(핀) 추가
            p0.addMarker(mOptions)
        }
        // 버튼 이벤트
        ImageButton!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val inputMethodManager =
                    getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager  // 버튼 클릭시 EditText 키보드 내리기.
                inputMethodManager.hideSoftInputFromWindow(
                    v?.windowToken,
                    0
                )   // 버튼 클릭시 EditText 키보드 내리기.
                var str = editText!!.text.toString()
                var addressList: List<Address>? = null
                try { // editText에 입력한 텍스트(주소, 지역, 장소 등)을 지오 코딩을 이용해 변환
                    addressList = geocoder!!.getFromLocationName(
                        str,  // 주소
                        10
                    ) // 최대 검색 결과 개수
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                System.out.println(addressList!![0].toString())
                // 콤마를 기준으로 split
                val splitStr: List<String> = addressList[0].toString().split(",")
                val address = splitStr[0]
                    .substring(splitStr[0].indexOf("\"") + 1, splitStr[0].length - 2) // 주소
                println(address)
                val latitude =
                    splitStr[10].substring(splitStr[10].indexOf("=") + 1) // 위도
                val longitude =
                    splitStr[12].substring(splitStr[12].indexOf("=") + 1) // 경도
                println(latitude)
                println(longitude)
                company_address = address
                cp_lat = latitude
                cp_lng = longitude
                println(company_address)
                println(cp_lat)
                println(cp_lng)
                // 좌표(위도, 경도) 생성
                val point = LatLng(latitude.toDouble(), longitude.toDouble())
                // 마커 생성
                val mOptions2 = MarkerOptions()
                mOptions2.title("검색 결과")
                mOptions2.snippet(address)
                mOptions2.position(point)
                // 마커 추가
                mMap.addMarker(mOptions2)
                // 해당 좌표로 화면 줌
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point, 15f))
            }
        })
        ////////////////////
        // Add a marker in seoul and move the camera
        val seoul = LatLng(37.566, 126.978)
        mMap.addMarker(MarkerOptions().position(seoul).title("Marker in seoul"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(seoul))
    }

    private fun Create_Company(
        company_name: String?,
        people_num: String?,
        cp_lat: String?,
        cp_lng: String?,
        company_address: String?,
        owner_uid: String?,
        company_code: String?,
        cp_group: String?
    ) {
        compositeDisposable.add(
            serviceapi.Create_Company(
                company_name,
                people_num,
                cp_lat,
                cp_lng,
                company_address,
                owner_uid,
                company_code,
                cp_group
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ message ->
                    Toast.makeText(this, "회사 생성 완료!@###.", Toast.LENGTH_SHORT).show()
                },
                    { thr ->
                        Log.e("에러메세지", thr.message)
                    })
        )

    }


}
