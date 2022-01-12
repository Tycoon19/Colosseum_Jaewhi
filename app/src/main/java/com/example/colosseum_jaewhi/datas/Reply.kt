package com.example.colosseum_jaewhi.datas

import org.json.JSONObject

class Reply {

    var id = 0
    var content = ""

    lateinit var selectedSide : Side

    companion object{

        fun getReplyFromJson(jsonObj : JSONObject) : Reply{

            val resultReply = Reply()

            resultReply.id = jsonObj.getInt("id")
            resultReply.content = jsonObj.getString("content")

//            중괄호 따서 대입해서 한 큐에 넣어주기.
            resultReply.selectedSide = Side.getSideFromJson(jsonObj.getJSONObject("selected_side"))

            return resultReply

        }

    }

}