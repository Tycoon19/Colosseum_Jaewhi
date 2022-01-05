package com.example.colosseum_jaewhi.utils

import android.util.Log
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class ServerUtil {

//   어떤 내용? => 서버 연결 전담.

//  자바의 Static과 비슷한 개념으로서 다른 곳에서 ServerUtil.BASE_URL 을 바로 사용하기 위해 companion object를 사용함.
    companion object{

//        모든 기능의 기본이 되는 주소. 많이 써야 해서 변수로 지정해버렸다잉~
        val BASE_URL = "http://54.180.52.26"

//    로그인 하는 기능 -> 함수를 만들어라
        fun postRequestLogin(email : String, pw : String){

//            입력받은 email, pw 서버 전달 => 로그인 기능 POST /user 로 전달. => 요청(request) 실행.
//            직접 짜기 어려우니 라이브러리(okHttp) 활용해서 짜보자.

//            http://54.180.52.26/user + POST + 파라미터들 첨부. 를 고려해줘야 함!!!

//            호스트 주소 + 기능 주소 결합
        val urlString = "${BASE_URL}/user"

//            POST 방식 => 파라미터를 폼데이터(폼바디)에 담아주자.
//            .add("--") 큰따옴표 안에는 서버가 달라고 하는 이름표를 적어주고 그 다음엔 내가 설정한 변수를 담아준다.
        val formdata = FormBody.Builder()
            .add("email",email)
            .add("password",pw)
            .build()


//          어디로 => 어떻게 => 어떤 데이터를 들고 가는지를 모두 종합해둔, Request 변수 생성.
        val request = Request.Builder()
//                먼저 어디로 갈꺼니?
            .url(urlString)
//                어떤 방식으로 뭘 들고 갈꺼니?
            .post(formdata)
            .build()

//          클라이언로써의 동작 : Request 요청 실행. = OkHttp 라이브러리 지원

        val client = OkHttpClient()
//          새로 호출 좀 해줘~ => 실제로 서버에 요청 날리기. => 갔다 와서는 뭘 할건지?{enqueue에 관한 것.(SharedPreference처럼 가이드북 생성 메타 따라가기)}
            client.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {
//                  서버 연결 자체를 실패한 경우(서버 마비, 인터넷 단선...)
//                  로그인에 실패했다는 개념이 아님!!
                }

                override fun onResponse(call: Call, response: Response) {
//                  로그인 성공 or 실패 처럼 응답이 돌아온 경우.

//                  본문을 String으로 저장해보자. 원래는 toString()인데 여기선 .string()으로 해줘야 함.
                    val bodyString = response.body!!.string()

//                  bodyString에는 한글이 깨져있다. => JSONObject로 변환하면, 한글 정상 처리.
                    val jsonObj = JSONObject(bodyString).toString()

                    Log.d("응답본문",jsonObj)
                }


            })

//      여기까지 하면 ServerUtil -> API 서버 의 구현은 끝난거임. 아직 Activity에서 이 기능을

        }


    }

}