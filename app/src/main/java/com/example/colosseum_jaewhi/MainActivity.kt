package com.example.colosseum_jaewhi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.colosseum_jaewhi.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setValues()
        setupEvents()

}
    override fun setupEvents() {

        loginBtn.setOnClickListener {
//          입력한 이메일, 비밀번호가 뭔지 변수에 저장.
            val inputEmail = emailEdt.text.toString()
            val inputPw = passwordEdt.text.toString()

//          서버에 실제 회원이 맞는지 확인 요청 (Request)
//          밑에다 줄줄이 적어도 되는데 너무 길어지기 때문에 코드 정리용 ServerUtil.kt에 작성하고 불러오
            ServerUtil.postRequestLogin(inputEmail,inputPw)
        }

    }

    override fun setValues() {

    }


}