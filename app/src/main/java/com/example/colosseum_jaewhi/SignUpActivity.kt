package com.example.colosseum_jaewhi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.colosseum_jaewhi.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.emailEdt
import kotlinx.android.synthetic.main.activity_main.signupBtn
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

        signupBtn.setOnClickListener {

            val inputEmail = emailEdt.text.toString()
            val inputPw = passwordEdt.text.toString()
            val inputNickname = nicknameEdt.text.toString()

            ServerUtil.postRequestSignUp(inputEmail,inputPw,inputNickname, object : ServerUtil.JsonResponseHandler{
                override fun onResponse(jsonObj: JSONObject) {



                }


            })

        }

    }

    override fun setValues() {

    }


}