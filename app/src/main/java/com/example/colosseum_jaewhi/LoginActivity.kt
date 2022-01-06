package com.example.colosseum_jaewhi

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.colosseum_jaewhi.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.signupBtn
import org.json.JSONObject

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setValues()
        setupEvents()

}
    override fun setupEvents() {

        signupBtn.setOnClickListener {

            val myIntent = Intent(mContext,SignUpActivity::class.java)
            startActivity(myIntent)

        }

        loginBtn.setOnClickListener {
//          입력한 이메일, 비밀번호가 뭔지 변수에 저장.
            val inputEmail = emailEdt.text.toString()
            val inputPw = passwordEdt.text.toString()

//          서버에 실제 회원이 맞는지 확인 요청 (Request)
//          밑에다 줄줄이 적어도 되는데 너무 길어지기 때문에 코드 정리용 ServerUtil.kt에 작성하고 불러오기
//          서버에 로그인하러 email, pw 갔다 와주세요. 하지만 갔다와서 뭐할건가요? ==> 일종의 가이드북!!!! 메타 가져오자ㅋㅋ
            ServerUtil.postRequestLogin(inputEmail,inputPw,object : ServerUtil.JsonResponseHandler{
                override fun onResponse(jsonObj: JSONObject) {

//                    jsonObj : 서버에서 내려준 본문을 JSON형태로 가공해준 결과물.
//                    => 이 내부를 파싱(분석)해서, 상황에 따른 대응.
//                    => ex. 로그인 실패시, 그 이유를 토스트로 띄운다던지.

                    val code = jsonObj.getInt("code")

                    if (code == 200){
//                        로그인 성공
                    }
                    else{
//                        로그인 실패
                        val message = jsonObj.getString("message")
//                        여기까지하고 돌리면 앱이 죽는다. 왜냐면 서버통신은 백그라운드 작업인데 Toast로 먼저 띄워버리려 하니 순서가 꼬이기 때문.
//                        그래서 Ui, 화면 전담하는 친구(runOnUiThread)가 담당해달라고 처리하면 만사ok.
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