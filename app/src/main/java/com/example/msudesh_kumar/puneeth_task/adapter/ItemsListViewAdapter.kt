package com.example.msudesh_kumar.puneeth_task.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView
import com.example.msudesh_kumar.puneeth_task.R
import com.example.msudesh_kumar.puneeth_task.adapter.interfaces.OnClicker
import com.example.msudesh_kumar.puneeth_task.model.Task
import com.example.msudesh_kumar.puneeth_task.utils.DataExtractor
import java.text.DateFormatSymbols
import java.util.*

class ItemsListViewAdapter(taskList : List<Task>, onClickCallback : OnClicker) : BaseAdapter() {
    private var taskList : List<Task> = taskList
    private var mOnClickCallback = onClickCallback

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        //val view = LayoutInflater.from(p2?.context).inflate()
        var view = LayoutInflater.from(p2?.context).inflate(R.layout.item_row, p2, false)
        val checkBox = view?.findViewById<CheckBox>(R.id.item_row_checkBox)
        val title = view?.findViewById<TextView>(R.id.item_row_title_textView)
        val date = view?.findViewById<TextView>(R.id.item_row_date_textView)

        when(taskList[p0].status == "COMPLETED"){
            true -> {
                checkBox?.isChecked = true
                //checkBox?.isClickable = false
                title?.setTextColor(Color.parseColor("#d2d3d5"))
            }
            else -> {
                checkBox?.isChecked = false
                //checkBox?.isClickable = true
                title?.setTextColor(Color.parseColor("#000000"))
            }
        }

        title?.text = when{
            (taskList[p0].description.length>15) -> {
                taskList[p0].description.subSequence(0,16).toString() + "....."
            }
            else -> {
                taskList[p0].description
            }
        }
        // taskList[p0].description.subSequence(0,22).toString()
        //date?.text = DataExtractor().extractDate(taskList[p0].scheduledDate).toString()
        val dateExtract = DataExtractor().extractDate(taskList[p0].scheduledDate)
        val dd  = dateExtract.get(Calendar.DATE)
        val mm = dateExtract.get(Calendar.MONTH)
        val month = DateFormatSymbols().shortMonths[mm]
        val datefinal = month + " " + dd.toString()
        date?.text = datefinal

        view?.setOnClickListener {
            mOnClickCallback.onClicked(taskList[p0])
        }

        return view
    }

    override fun getItem(p0: Int): Any {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return taskList[p0]
    }

    override fun getItemId(p0: Int): Long {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return p0.toLong()
    }

    override fun getCount(): Int {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return taskList.size
    }
}