package com.example.colosseum_jaewhi

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

abstract class BaseActivity : AppCompatActivity() {

    // 밑에서 자동완성해서 쓰지말고 여기서 lateinit var 로 선언해주자.
    lateinit var backBtn : ImageView


    val mContext = this

    abstract fun setupEvents()
    abstract fun setValues()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 액션바가 있는 화면만, 커스텀액션바 세팅 실행.
        // 액션바 있나요? 있다면 let{...}실행해주세요.
        supportActionBar?.let {
            setCustomActionBar()
        }


    }

    fun setCustomActionBar(){

        val defaultActionBar = supportActionBar!!
        defaultActionBar.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        defaultActionBar.setCustomView(R.layout.my_custom_action_bar)

        val myToolbar = defaultActionBar.customView.parent as Toolbar
        myToolbar.setContentInsetsAbsolute(0,0)

        backBtn = defaultActionBar.customView.findViewById(R.id.backBtn)
        backBtn.setOnClickListener {
            finish()
        }

    }

}