package com.example.colosseum_jaewhi.datas

import org.json.JSONObject
import java.io.Serializable

class Side : Serializable{
    var id = 0
    var topicId = 0
    var title = ""
    var voteCount = 0

    companion object{

        fun getSideFromJson(jsonObj : JSONObject) : Side{

            val resultSide = Side()

            resultSide.id = jsonObj.getInt("id")
            resultSide.topicId = jsonObj.getInt("topic_id")
            resultSide.title = jsonObj.getString("title")
            resultSide.voteCount = jsonObj.getInt("vote_count")

            return resultSide

        }

    }

}