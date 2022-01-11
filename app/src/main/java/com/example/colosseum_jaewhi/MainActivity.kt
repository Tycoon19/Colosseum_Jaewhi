package com.example.colosseum_jaewhi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.colosseum_jaewhi.adapters.TopicAdapter
import com.example.colosseum_jaewhi.datas.Topic
import com.example.colosseum_jaewhi.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : BaseActivity() {

    val mTopicList = ArrayList<Topic>() // 미리 ArrayList의 형태로 Topic을 담겠다라고 객체화해서 저장해줌.

    lateinit var mTopicAdapter : TopicAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
}
    override fun setupEvents() {

        topicListView.setOnItemClickListener { parent, view, position, id ->

            val clickedTopic = mTopicList[position]

            val myIntent = Intent(mContext, ViewTopicDetailActivity::class.java)
            myIntent.putExtra("topic",clickedTopic)
            startActivity(myIntent)

        }

    }

    override fun setValues() {

        getTopicListFromServer()

        mTopicAdapter = TopicAdapter(mContext, R.layout.topic_list_item,mTopicList)
        topicListView.adapter = mTopicAdapter

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
//                index라는 변수를 이용해 각 Obj들을 훑어서 정보를 긁어오자 => 이걸 Topic클래스 형태로 변환시키고(Topic.kt > getTopicDataFromJson) => mTopicList에 추가시키자.

                for (index in 0 until topicsArr.length()){

//                    index위치에 맞는 주제들을 Topic클래스 형태로 변환.

                    val topicObj = topicsArr.getJSONObject(index)
                    val topicData = Topic.getTopicDataFromJson(topicObj)

//                    변환된 주제들을 mTopicList에 추가시키자.
                    mTopicList.add(topicData)

                }

//                어댑터가 먼저 세팅 되고 => 나중에 목록이 추가. 서버통신은 먼 길을 다녀오기 때문에!
//                여기까지만 하면 앱이 죽는다. 왜냐하면 결국 목록이 변경됐다는건 새로고침이 필요하다는 건데 이는 UI에 영향을 주기 때문에!

                runOnUiThread {
                    mTopicAdapter.notifyDataSetChanged()
                }

            }

        })

    }

}