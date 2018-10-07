package com.example.msudesh_kumar.puneeth_task.fragment


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView

import com.example.msudesh_kumar.puneeth_task.R
import com.example.msudesh_kumar.puneeth_task.adapter.ItemsListViewAdapter
import com.example.msudesh_kumar.puneeth_task.adapter.interfaces.OnClicker
import com.example.msudesh_kumar.puneeth_task.api.ApiClient
import com.example.msudesh_kumar.puneeth_task.api.ApiInterface
import com.example.msudesh_kumar.puneeth_task.model.Task
import com.example.msudesh_kumar.puneeth_task.utils.DataExtractor
import com.example.msudesh_kumar.puneeth_task.utils.UIUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.Comparator

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class LaterFragment : Fragment() {

    val title = "Later"

    var mContext : Context? = null
    private lateinit var mPendingList : ListView
    private lateinit var mCompletedList : ListView
    var taskList : ArrayList<Task> = ArrayList()
    var pendingTaskList : ArrayList<Task> = ArrayList()
    var completedTaskList : ArrayList<Task> = ArrayList()

    var mPendingTasksAdapter : ItemsListViewAdapter? = null
    var mCompletedTasksAdapter : ItemsListViewAdapter? = null

    private lateinit var mTasksList : List<Task>
    val tasks : ArrayList<Task> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_later, container, false)
        mPendingList = view.findViewById(R.id.today_fragment_pending_task_listView)
        mCompletedList = view.findViewById(R.id.today_fragment_completed_task_listView)

        val apiService = ApiClient().getClient()?.create(ApiInterface::class.java)
        val call : Call<List<Task>>? = apiService?.getTasks()
        call?.enqueue(object : Callback<List<Task>> {
            override fun onFailure(call: Call<List<Task>>, t: Throwable) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                Log.d("Respose failed -> ", t.toString())
            }

            override fun onResponse(call: Call<List<Task>>, response: Response<List<Task>>) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                mTasksList = response.body()!!
                tasks.addAll(mTasksList)
                Log.d("Success result -> ", mTasksList.toString())
                tasks.forEach {
                    if (DataExtractor().extractDate(it.scheduledDate).time > Calendar.getInstance().time) {
                        taskList.add(it)
                    }
                }
                taskList.sortWith(Comparator { p0, p1 ->
                    //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    (DataExtractor().extractDate(p0?.scheduledDate!!).time).compareTo((DataExtractor().extractDate(p1?.scheduledDate!!).time))
                })
                taskList.forEach {
                    if (it.status == "PENDING")
                    {
                        pendingTaskList.add(it)
                    }
                    else if (it.status == "COMPLETED")
                    {
                        completedTaskList.add(it)
                    }
                }
                mPendingTasksAdapter = ItemsListViewAdapter(pendingTaskList, object : OnClicker {
                    override fun onClicked(task: Task) {
                        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                        val status = task.status
                        when(status == "COMPLETED"){
                            true -> {
                                completedTaskList.remove(task)
                                var newtask = task
                                newtask.status = "PENDING"
                                pendingTaskList.add(newtask)
                            }
                            false -> {
                                pendingTaskList.remove(task)
                                var newtask = task
                                newtask.status = "COMPLETED"
                                completedTaskList.add(newtask)
                            }
                        }
                        mPendingTasksAdapter?.notifyDataSetChanged()
                        UIUtils.setListViewHeightBasedOnItems(mPendingList)
                        mCompletedTasksAdapter?.notifyDataSetChanged()
                        UIUtils.setListViewHeightBasedOnItems(mCompletedList)
                    }

                })
                mPendingList.adapter = mPendingTasksAdapter
                UIUtils.setListViewHeightBasedOnItems(mPendingList)

                mCompletedTasksAdapter = ItemsListViewAdapter(completedTaskList, object : OnClicker{
                    override fun onClicked(task: Task) {
                        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                        val status = task.status
                        when(status == "COMPLETED"){
                            true -> {
                                completedTaskList.remove(task)
                                var newtask = task
                                newtask.status = "PENDING"
                                pendingTaskList.add(newtask)
                            }
                            false -> {
                                pendingTaskList.remove(task)
                                var newtask = task
                                newtask.status = "COMPLETED"
                                completedTaskList.add(newtask)
                            }
                        }
                        mPendingTasksAdapter?.notifyDataSetChanged()
                        UIUtils.setListViewHeightBasedOnItems(mPendingList)
                        mCompletedTasksAdapter?.notifyDataSetChanged()
                        UIUtils.setListViewHeightBasedOnItems(mCompletedList)
                    }

                })
                mCompletedList.adapter = mCompletedTasksAdapter
                UIUtils.setListViewHeightBasedOnItems(mCompletedList)
            }

        })

        return view
    }

}
