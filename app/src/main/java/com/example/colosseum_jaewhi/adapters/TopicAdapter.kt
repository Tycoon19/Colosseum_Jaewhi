package com.example.colosseum_jaewhi.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.colosseum_jaewhi.R
import com.example.colosseum_jaewhi.datas.Topic

class TopicAdapter(
    val mContext : Context,
    resId : Int, // 앞에 m자의 유무에 따라 val의 유무가 결정된다.
    val mList : List<Topic>
) : ArrayAdapter<Topic>(mContext,resId,mList) {

    val mInflater = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView
        if (tempRow == null){
            tempRow = mInflater.inflate(R.layout.topic_list_item, null)
        }

        val row = tempRow!!


        return row

    }

}