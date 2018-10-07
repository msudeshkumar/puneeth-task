package com.example.msudesh_kumar.puneeth_task.utils

import java.sql.Time
import java.text.DecimalFormat
import java.time.LocalDate
import java.util.*

class DataExtractor {

    fun extractDate(date : String) : Calendar
    {
        val dd = date.subSequence(7,9).toString().toInt()
        val mm  = date.subSequence(5,7).toString().toInt()
        val yyyy  = date.subSequence(0,4).toString().toInt()

        val calendar = Calendar.getInstance()
        calendar.set(yyyy,mm,dd)

        return calendar
    }

    fun extractHour(date: String) : String
    {
        val formatter = DecimalFormat("00")
        var hh = date.subSequence(8,10).toString().toInt()
        val mm = date.subSequence(10,12).toString().toInt()
        var am_pm : String

        when(hh>12)
        {
            true -> {
                hh -= 12
                am_pm = "PM"
            }
            else -> {
                am_pm = "AM"
            }
        }


        return "${formatter.format(hh)}:${formatter.format(mm)}$am_pm"
    }

}