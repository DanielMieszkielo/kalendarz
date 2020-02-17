package com.workfort.calender

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast

import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Calendar
import java.util.Collections
import java.util.Collections.emptyList
import java.util.Date

class MainActivity : AppCompatActivity(), CalendarPickerView.OnDateSelectedListener {
    private var checkInTv: TextView? = null
    private var checkOutTv: TextView? = null
    private var nightCountTv: TextView? = null
    private var calendarPickerView: CalendarPickerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date_range)
        checkInTv = findViewById<View>(R.id.check_in_tv) as TextView
        checkOutTv = findViewById<View>(R.id.check_out_tv) as TextView
        nightCountTv = findViewById<View>(R.id.night_count_tv) as TextView
        calendarPickerView = findViewById<View>(R.id.calendar_view) as CalendarPickerView
        calendarPickerView!!.setOnDateSelectedListener(this)

        val list = ArrayList<String>()
        list.add("2018-09-11")
        list.add("2018-09-15")
        list.add("2018-09-16")
        list.add("2018-09-18")
        calendarPickerView!!.setInactiveDate(list)

        checkInTv!!.text = ""
        checkOutTv!!.text = ""
        checkOutTv!!.hint = "---"

        calendarPickerView!!.decorators = emptyList<CalendarCellDecorator>()
        val nextYear = Calendar.getInstance()
        nextYear.add(Calendar.YEAR, 2)
        calendarPickerView!!.init(Date(), nextYear.time).inMode(CalendarPickerView.SelectionMode.RANGE)
    }

    override fun onDateSelected(date: Date) {
        val dateFormat = SimpleDateFormat("MMM d, yyyy")
        val selectedDateList = calendarPickerView!!.selectedDates

        if (selectedDateList.size > 1) {
            checkInTv!!.text = dateFormat.format(selectedDateList[0])
            checkOutTv!!.text = dateFormat.format(selectedDateList[selectedDateList.size - 1])
        } else {
            checkInTv!!.text = dateFormat.format(date)
            checkOutTv!!.hint = getString(R.string.select_date)
        }

        nightCountTv!!.setText(String.format(getString(R.string.nights_count), selectedDateList.size))

    }

    override fun onDateUnselected(date: Date) {

    }

    fun onClickFabButton(view: View) {
        Toast.makeText(this, "Date count =" + calendarPickerView!!.selectedDates.size, Toast.LENGTH_SHORT).show()
    }
}
