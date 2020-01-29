package com.example.workman


import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_schedule.*
import kotlinx.android.synthetic.main.fragment_schedule.view.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * 근무일정
 */
class ScheduleFragment : Fragment() {
    val DATE_FORMAT : String = "yyyy MMMM"
    var dateFormat : String = ""
    val currentDate = Calendar.getInstance()
    var cell = arrayListOf<Date>()
    var cell2 = arrayListOf<Date>()
    var cell3 = arrayListOf<Date>()
    var cell4 = arrayListOf<Date>()
    var cell5 = arrayListOf<Date>()
    var calendar = currentDate.clone() as Calendar
    var i : Int = 0
    var j : Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 툴바 타이틀 변경하기
        activity!!.toolbar.title = "근무일정"
        activity!!.toolbar.setTitleTextColor(Color.WHITE)

        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_schedule, container, false)

        // 플로팅액션버튼 부분
        // 근무조 설정
        rootView.f_schedule_Floating_menu_item1.setOnClickListener {
            val nextIntent = Intent(activity, ScheduleGroupSet1::class.java)
            startActivity(nextIntent)
        }

        //근무리스트 설정
        rootView.f_schedule_Floating_menu_item2.setOnClickListener {
            val nextIntent = Intent(activity, ScheduleListSet::class.java)
            startActivity(nextIntent)
        }

        // 달력부분(보이는 형식)
        dateFormat = DATE_FORMAT

        // update 달력 타이틀
        val sdf : DateFormat = SimpleDateFormat(dateFormat)
        rootView.calendar_current_title.setText(sdf.format(currentDate.time))
        rootView.calendar_test2.setText("월일수 "+currentDate.getActualMaximum(Calendar.DAY_OF_MONTH))

        // 이전달 클릭
        rootView.calendar_prev_text.setOnClickListener {
            //달력 변수들 초기화
            i = 0
            cell.clear()
            cell2.clear()
            cell3.clear()
            cell4.clear()
            cell5.clear()

            // 타이틀부분
            currentDate.add(Calendar.MONTH,-1)
            calendar = currentDate.clone() as Calendar
            calendar_current_title.setText(sdf.format(currentDate.time))
            calendar_test2.setText("월일수 "+currentDate.getActualMaximum(Calendar.DAY_OF_MONTH))

            // 달력 최종호출
            CalendarGet(rootView)
        }

        // 다음달 클릭
        rootView.calendar_next_text.setOnClickListener {
            //달력 변수들 초기화
            i = 0
            cell.clear()
            cell2.clear()
            cell3.clear()
            cell4.clear()
            cell5.clear()

            // 타이틀부분
            currentDate.add(Calendar.MONTH,1)
            calendar = currentDate.clone() as Calendar
            calendar_current_title.setText(sdf.format(currentDate.time))
            calendar_test2.setText("월일수 "+currentDate.getActualMaximum(Calendar.DAY_OF_MONTH))

            // 달력 최종호출
            CalendarGet(rootView)
        }
        // update 달력 타이틀 끝.

        // 달력 최종호출
        CalendarGet(rootView)

        return rootView
    }

    //달력 최종호출
    fun CalendarGet(rootView:View) {
        // 셀채우기 시작
        CalendarDay(cell,-1)
        CalendarDay(cell2,-1)
        CalendarDay(cell3,-1)
        CalendarDay(cell4,-1)
        if(currentDate.getActualMaximum(Calendar.DAY_OF_MONTH) > 28) { // 마지막주차가 28일보다 높다면...
            CalendarDay(cell5,currentDate.getActualMaximum(Calendar.DAY_OF_MONTH)-28)
        }
        // 셀채우기 끝

        // 달력 리싸이클러뷰
        CalendarRecycler(rootView)
    }

    // 달력 리싸이클러뷰
    fun CalendarRecycler(rootView:View){
        rootView.calendar_recyclerview1.setHasFixedSize(true)
        rootView.calendar_recyclerview1.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,false)
        rootView.calendar_recyclerview1.adapter = CalendarAdapter(activity!!,cell)

        rootView.calendar_recyclerview2.setHasFixedSize(true)
        rootView.calendar_recyclerview2.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,false)
        rootView.calendar_recyclerview2.adapter = CalendarAdapter(activity!!,cell2)

        rootView.calendar_recyclerview3.setHasFixedSize(true)
        rootView.calendar_recyclerview3.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,false)
        rootView.calendar_recyclerview3.adapter = CalendarAdapter(activity!!,cell3)

        rootView.calendar_recyclerview4.setHasFixedSize(true)
        rootView.calendar_recyclerview4.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,false)
        rootView.calendar_recyclerview4.adapter = CalendarAdapter(activity!!,cell4)

        rootView.calendar_recyclerview5.setHasFixedSize(true)
        rootView.calendar_recyclerview5.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,false)
        rootView.calendar_recyclerview5.adapter = CalendarAdapter(activity!!,cell5)
    }

    // 달력 채워넣기. (셀채우기, 1주차부터 5주차까지 각 일수 채우기)
    fun CalendarDay(list: ArrayList<Date>, a:Int){
        if(a > 0) { // 달력 5주차부터는 일수에 따라 달라야댐.
            while (list.size < a+1) {
                if (i == 29) {
                    list.add(currentDate.time)
                    calendar.set(Calendar.DAY_OF_MONTH, i)
                    list.add(calendar.time)
                } else {
                    calendar.set(Calendar.DAY_OF_MONTH, i)
                    list.add(calendar.time)
                }
                i++
            }
        }
        else { // 달력 1~4주차까지는 똑같음.
            while (list.size < 8) {
                if (i == 0) {
                    list.add(currentDate.time)
                } else if (i == 8) {
                    list.add(currentDate.time)
                    calendar.set(Calendar.DAY_OF_MONTH, i)
                    list.add(calendar.time)
                } else if (i == 15) {
                    list.add(currentDate.time)
                    calendar.set(Calendar.DAY_OF_MONTH, i)
                    list.add(calendar.time)
                } else if (i == 22) {
                    list.add(currentDate.time)
                    calendar.set(Calendar.DAY_OF_MONTH, i)
                    list.add(calendar.time)
                } else {
                    calendar.set(Calendar.DAY_OF_MONTH, i)
                    list.add(calendar.time)
                }
                i++
            }
        }
    }

}

// 달력 리싸이클러뷰.
class CalendarAdapter(private val context: Context, private val list: ArrayList<Date>) : RecyclerView.Adapter<CalendarAdapter.ViewHolder>(){
    val DATE_FORMAT : String = "d"
    val DATE_FORMAT2 : String = "E"
    var dateFormat : String = ""
    val currentDate = Calendar.getInstance()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context)
            .inflate(R.layout.item_view_calendar1,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        dateFormat = DATE_FORMAT
        val sdf : DateFormat = SimpleDateFormat(dateFormat)
        val sdf2 : DateFormat = SimpleDateFormat(DATE_FORMAT2)
        val calendar = currentDate.clone() as Calendar
        calendar.timeInMillis = list[position].time
        //holder.day_of_week.setText(""+sdf.format(list[position].time))
        //holder.day.setText(""+list[position].getDay())



        // 날짜&요일 넣기
        if(position == 0) {
            holder.day.setText("일자")
            holder.day_of_week.setText("요일")
        }else{
            // 요일 색깔넣기
            if(sdf2.format(calendar.time).equals("토")) {
                holder.day_of_week.setTextColor(Color.BLUE)
            }else if(sdf2.format(calendar.time).equals("일")){
                holder.day_of_week.setTextColor(Color.RED)
            }
            holder.day.setText(sdf.format(list[position].time))
            holder.day_of_week.setText(sdf2.format(calendar.time))

        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener {
        var day_of_week : TextView
        var day : TextView

        init {
            day_of_week = itemView.findViewById(R.id.ItemViewCalendar1_TextView2)
            day = itemView.findViewById(R.id.ItemViewCalendar1_TextView1)
        }
        override fun onClick(v: View?) {

        }

    }

}
