package com.example.colosseum_jaewhi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.colosseum_jaewhi.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.json.JSONObject

class SignUpActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        setupEvents()
        setValues()
}
    override fun setupEvents() {
//      중복검사 버튼 Click
        emailCheckBtn.setOnClickListener {
//            입력한 이메일? => 중복 검사.
            val inputEmail = signupEmailEdt.text.toString()

//            서버에 중복 여부 확인. API 호출
            ServerUtil.getRequestDuplCheck("EMAIL",inputEmail,object : ServerUtil.JsonResponseHandler{
                override fun onResponse(jsonObj: JSONObject) {

                    val code = jsonObj.getInt("code")

                    runOnUiThread {

                        if (code == 200){
                            Toast.makeText(mContext, "사용해도 좋습니다.", Toast.LENGTH_SHORT).show()
                        }
                        else{
                            val message = jsonObj.getString("message")
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                        }

                    }

                }


            })



        }
//      회원가입 버튼 Click
        signupBtn.setOnClickListener {

            val inputEmail = signupEmailEdt.text.toString()
            val inputPw = signupPasswordEdt.text.toString()
            val inputNickname = signupNicknameEdt.text.toString()

            ServerUtil.putRequestSignUp(inputEmail,inputPw,inputNickname, object : ServerUtil.JsonResponseHandler{
                override fun onResponse(jsonObj: JSONObject) {

                    val code = jsonObj.getInt("code")

                    if (code == 200){
//                          가입한 사람의 닉네임 추출 => 토스트로 ~~님 환영합니다! + 회원가입화면 종료 => 로그인화면으로 복귀.
//                          jsonObj{ } => data { ] => user { } => 내부에서 nick_name String 추출.

                        val dataObj = jsonObj.getJSONObject("data")
                        val userObj = dataObj.getJSONObject("user")
                        val nickName = userObj.getString("nick_name")

                        runOnUiThread {
                            Toast.makeText(mContext,"${nickName}님, 환영합니다!",Toast.LENGTH_SHORT).show()
                            finish()
                        }


                    }
                    else{
//                          실패 사유를 => 서버가 주는 message에 담긴 문구로 출력.
                        val message = jsonObj.getString("message")
                        runOnUiThread {
                            Toast.makeText(mContext,message,Toast.LENGTH_SHORT).show()
                        }
                    }

                }


            })

        }

    }

    override fun setValues() {

    }


}