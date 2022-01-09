package com.example.colosseum_jaewhi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.colosseum_jaewhi.datas.Topic
import com.example.colosseum_jaewhi.utils.ServerUtil
import org.json.JSONObject

class MainActivity : BaseActivity() {

    val mTopicList = ArrayList<Topic>() // ArrayList를 미리 Topic을 담겠다라고 구체화 해줌.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
}
    override fun setupEvents() {

    }

    override fun setValues() {

        getTopicListFromServer()

    }

    fun getTopicListFromServer(){

//        서버에서 실제로 주제 목록을 받아오자. => /v2/main_info => ServerUtil에 기능 추가 필요.

        ServerUtil.getRequestMainInfo(mContext, object  : ServerUtil.JsonResponseHandler{
            override fun onResponse(jsonObj: JSONObject) {

//                서버에서 주제 목록을 받아온 상황.

                val dataObj = jsonObj.getJSONObject("data")
                val topicsArr = dataObj.getJSONArray("topics")
//                topicsArr 안에 있는 여러개의 주제들을 반복적으로 파싱. => for문 활용하자!
//                배열에 10개 주제 : index -> 0~9

                for (index in 0 until topicsArr.length()){

//                    index위치에 맞는 주제들을 Topic클래스 형태로 변환.

                    val topicObj = topicsArr.getJSONObject(index)
                    val topicData = Topic.getTopicDataFromJson(topicObj)

//                    변환된 주제들을 mTopicList에 추가시키자.
                    mTopicList.add(topicData)

                }

            }

        })

    }

}