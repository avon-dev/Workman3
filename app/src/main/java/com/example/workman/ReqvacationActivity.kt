package com.example.workman

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.workman.decorator.RangeDayDecorator
import com.example.workman.decorator.SaturdayDecorator
import com.example.workman.decorator.SundayDecorator
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.MaterialCalendarView.*
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import com.prolificinteractive.materialcalendarview.OnRangeSelectedListener
import kotlinx.android.synthetic.main.activity_reqvacation.*

class ReqvacationActivity : AppCompatActivity(), OnDateSelectedListener, OnRangeSelectedListener {
    override fun onDateSelected(
        widget: MaterialCalendarView,
        date: CalendarDay,
        selected: Boolean
    ) {

    }

    override fun onRangeSelected(widget: MaterialCalendarView, dates: MutableList<CalendarDay>) {
        if (dates.size > 0) {
            rangeDayDecorator.addFirstAndLast(dates[0], dates[dates.size - 1]);
            RV_calendarView.invalidateDecorators();
        }
    }

    private val sundayDecorator: SundayDecorator =
        SundayDecorator()
    private val saturdayDecorator: SaturdayDecorator =
        SaturdayDecorator()
    private val rangeDayDecorator: RangeDayDecorator =
        RangeDayDecorator(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reqvacation)

        RV_calendarView.selectionMode = SELECTION_MODE_MULTIPLE
        RV_calendarView.addDecorators(sundayDecorator, saturdayDecorator,rangeDayDecorator)
        RV_calendarView.setOnRangeSelectedListener(this)
        RV_calendarView.setOnDateChangedListener(this)
        RV_calendarView.isDynamicHeightEnabled = true
    }
}
