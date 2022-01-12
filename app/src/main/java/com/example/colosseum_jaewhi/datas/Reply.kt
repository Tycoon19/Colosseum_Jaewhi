package com.example.colosseum_jaewhi.datas

import org.json.JSONObject

class Reply {

    var id = 0
    var content = ""

    lateinit var selectedSide : Side

    var writerNickname = ""

    var likeCount = 0
    var dislikeCount = 0
    var myLike = false // Boolean의 의미임
    var myDislike = false

    companion object{

        fun getReplyFromJson(jsonObj : JSONObject) : Reply{

            val resultReply = Reply()

            resultReply.id = jsonObj.getInt("id")
            resultReply.content = jsonObj.getString("content")

//            중괄호 따서 대입해서 한 큐에 넣어주기.
            resultReply.selectedSide = Side.getSideFromJson(jsonObj.getJSONObject("selected_side"))

            resultReply.writerNickname = jsonObj.getJSONObject("user").getString("nick_name")

            resultReply.likeCount = jsonObj.getInt("like_count")
            resultReply.dislikeCount = jsonObj.getInt("dislike_count")
            resultReply.myLike = jsonObj.getBoolean("my_like")
            resultReply.myDislike = jsonObj.getBoolean("my_dislike")

            return resultReply

        }

    }

}