package com.example.colosseum_jaewhi.datas

import org.json.JSONObject

class Topic {

    var id = 0 // 나중에 Int가 들어올것이라는 명시.
    var title = "" // 나중에 String이 들어올것이라는 명시.
    var imageUrl = "" // 나중에 String이 들어올것이라는 명시.

    companion object{

//        적당한 JSONObject 하나를 넣어주면 => Topic 형태로 변환해주는 함수 작성하자. => 필요할때마다 함수를 호출하면 파싱이 알아서 되게!

//        JSONObject 줄께. Topic 다오ㅎ
        fun getTopicDataFromJson(jsonObj : JSONObject) : Topic{

            val resultTopic = Topic()

            resultTopic.id = jsonObj.getInt("id")
            resultTopic.title = jsonObj.getString("title")
            resultTopic.imageUrl = jsonObj.getString("img_url")

            return resultTopic

        }

    }

}