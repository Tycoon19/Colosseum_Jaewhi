package com.example.colosseum_jaewhi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.colosseum_jaewhi.datas.Topic

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



    }

}