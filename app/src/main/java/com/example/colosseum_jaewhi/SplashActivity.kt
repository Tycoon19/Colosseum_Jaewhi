package com.example.colosseum_jaewhi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.colosseum_jaewhi.utils.ContextUtil

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupEvents()
        setValues()
}

    override fun setupEvents() {

    }

    override fun setValues() {

//          3초 후에 검사 => 저장된 토큰이 있는지? 아니면, ""인지?
        val myHandler = Handler(Looper.getMainLooper())

        myHandler.postDelayed({
//              ""이라는건 저장된 토큰이 없다는 것 => 로그인을 해야한다!!
            if (ContextUtil.getToken(mContext) == ""){
                val myIntent = Intent(mContext,LoginActivity::class.java)
                startActivity(myIntent)
            }
//              저장된 토큰이 잇다 => 로그인이 되어있다 => 메인화면으로 이동.
            else{
                val myIntent = Intent(mContext,MainActivity::class.java)
                startActivity(myIntent)
            }
            finish()

        },3000)
    }


}