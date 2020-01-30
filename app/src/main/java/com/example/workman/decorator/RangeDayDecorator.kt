package com.example.workman.decorator

import android.text.method.TextKeyListener.clear
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.DayViewFacade
import android.R
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.text.style.ForegroundColorSpan
import androidx.core.content.ContextCompat
import com.prolificinteractive.materialcalendarview.DayViewDecorator


class RangeDayDecorator(context: Context) : DayViewDecorator {

    private val list = HashSet<CalendarDay>()

    override fun shouldDecorate(day: CalendarDay): Boolean {
        return list.contains(day)
    }

    override fun decorate(view: DayViewFacade) {
        return
    }

    /**
     * We're changing the dates, so make sure to call [MaterialCalendarView.invalidateDecorators]
     */
    fun addFirstAndLast(first: CalendarDay, last: CalendarDay) {
        list.clear()
        list.add(first)
        list.add(last)
    }
}