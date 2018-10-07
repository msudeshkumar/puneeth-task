package com.example.msudesh_kumar.puneeth_task.utils

import android.widget.ListView
import android.view.ViewGroup


object UIUtils {

    /**
     * Sets ListView height dynamically based on the height of the items.
     *
     * @param listView to be resized
     * @return true if the listView is successfully resized, false otherwise
     */
    fun setListViewHeightBasedOnItems(listView: ListView): Boolean {

        val listAdapter = listView.adapter
        if (listAdapter != null) {

            val numberOfItems = listAdapter.count

            // Get total height of all items.
            var totalItemsHeight = 0
            for (itemPos in 0 until numberOfItems) {
                val item = listAdapter.getView(itemPos, null, listView)
                item.measure(0, 0)
                totalItemsHeight += item.measuredHeight //- 25
                totalItemsHeight += item.height //- 25
            }

            // Get total height of all item dividers.
            //val totalDividersHeight = listView.dividerHeight * (numberOfItems - 1) //- 25
            val totalDividersHeight = listView.dividerHeight * (numberOfItems - 1) //- 25

            // Set list height.
            val params = listView.layoutParams
            params.height = totalItemsHeight + totalDividersHeight - (15 * numberOfItems)
            listView.layoutParams = params
            listView.requestLayout()

            return true

        } else {
            return false
        }

    }
}

