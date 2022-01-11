package com.example.colosseum_jaewhi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.colosseum_jaewhi.datas.Topic
import kotlinx.android.synthetic.main.activity_view_topic_detail.*

class ViewTopicDetailActivity : BaseActivity() {

    lateinit var mTopic : Topic

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_topic_detail)
        setupEvents()
        setValues()
}

    override fun setupEvents() {

    }

    override fun setValues() {

        mTopic = intent.getSerializableExtra("topic") as Topic

        topicTitleTxt.text = mTopic.title
        Glide.with(mContext).load(mTopic.imageUrl).into(topicImg)

    }


}