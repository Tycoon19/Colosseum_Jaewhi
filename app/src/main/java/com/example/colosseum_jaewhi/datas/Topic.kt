package com.example.colosseum_jaewhi.datas

import org.json.JSONObject
import java.io.Serializable

class Topic : Serializable{

    var id = 0 // 나중에 Int가 들어올것이라는 명시.
    var title = "" // 나중에 String이 들어올것이라는 명시.
    var imageUrl = "" // 나중에 String이 들어올것이라는 명시.

    val sides = ArrayList<Side>() // side 하나하나를 파싱하는 데이터클래스를 만들자.

    companion object{

//        적당한 JSONObject 하나를 넣어주면 => Topic 형태로 변환해주는 함수 작성하자. => 필요할때마다 함수를 호출하면 파싱이 알아서 되게!

//        JSONObject 줄께. Topic 다오ㅎ
        fun getTopicDataFromJson(jsonObj : JSONObject) : Topic{

            val resultTopic = Topic()

            resultTopic.id = jsonObj.getInt("id")
            resultTopic.title = jsonObj.getString("title")
            resultTopic.imageUrl = jsonObj.getString("img_url")

            val sideArr = jsonObj.getJSONArray("sides")

            for (i  in 0 until sideArr.length()){

//                토픽마다 하위정보로 달린 => 선택진영을 파싱.
                val sideObj = sideArr.getJSONObject(i)
                val side = Side.getSideFromJson(sideObj)

                resultTopic.sides.add(side)

            }

            return resultTopic

        }

    }

}