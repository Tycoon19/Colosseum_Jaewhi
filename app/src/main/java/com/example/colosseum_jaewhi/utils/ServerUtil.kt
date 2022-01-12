package com.example.colosseum_jaewhi.utils

import android.content.Context
import android.util.Log
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import org.json.JSONObject
import java.io.IOException

class ServerUtil {
//  Activity에서 응답내용에 따른 처리를 해줘야함. ServerUtil에서는 응답결과를 화면단에게 전달해줘야 함.
//  ServerUtil에서 다시 Activity로 역으로 돌아가게 하는 것이 Interface의 개념.
    interface JsonResponseHandler{
        fun onResponse(jsonObj : JSONObject)

    }

//   어떤 내용? => 서버 연결 전담.

//  자바의 Static과 비슷한 개념으로서 다른 곳에서 ServerUtil.BASE_URL 을 바로 사용하기 위해 companion object를 사용함.
    companion object{

//        모든 기능의 기본이 되는 주소. 많이 써야 해서 변수로 지정해버렸다잉~
        val BASE_URL = "http://54.180.52.26"

//    로그인 하는 기능 -> 함수를 만들어라

        fun postRequestLogin(email : String, pw : String, handler : JsonResponseHandler?){

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
                    val jsonObj = JSONObject(bodyString)

                    Log.d("응답본문",jsonObj.toString())

//                    handler 변수가 null이 아니라, (실체가 있다면)
//                    그 내부에 적힌 내용 실행.

                    handler?.onResponse(jsonObj)

                }


            })

//      여기까지 하면 ServerUtil -> API 서버 의 구현은 끝난거임. 아직 Activity에서 이 기능을

        }


//    회원가입 하는 기능

        fun putRequestSignUp(email : String, pw : String, nick : String, handler : JsonResponseHandler?){

//            입력받은 email, pw, nick 서버 전달 => 회원가입 기능 PUT /user 로 전달. => 요청(request) 실행.
//            직접 짜기 어려우니 라이브러리(okHttp) 활용해서 짜보자.

//            http://54.180.52.26/user + PUT + 파라미터들 첨부. 를 고려해줘야 함!!!

//            호스트 주소 + 기능 주소 결합
        val urlString = "${BASE_URL}/user"

//            PUT 방식 => 파라미터를 폼데이터(폼바디)에 담아주자.
//            .add("--") 큰따옴표 안에는 서버가 달라고 하는 이름표를 적어주고 그 다음엔 내가 설정한 변수를 담아준다.
        val formdata = FormBody.Builder()
            .add("email",email)
            .add("password",pw)
            .add("nick_name",nick)
            .build()


//          어디로 => 어떻게 => 어떤 데이터를 들고 가는지를 모두 종합해둔, Request 변수 생성.
        val request = Request.Builder()
//                먼저 어디로 갈꺼니?
            .url(urlString)
//                어떤 방식으로 뭘 들고 갈꺼니?
            .put(formdata)
            .build()

//          클라이언로써의 동작 : Request 요청 실행. = OkHttp 라이브러리 지원
//          이 밑으로는 항상 동일한 코드가 실행됨. 따로 건들이지 말자.
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
                val jsonObj = JSONObject(bodyString)

                Log.d("응답본문",jsonObj.toString())

//                    handler 변수가 null이 아니라, (실체가 있다면)
//                    그 내부에 적힌 내용 실행.

                handler?.onResponse(jsonObj)

            }


        })

//      여기까지 하면 ServerUtil -> API 서버 의 구현은 끝난거임. 아직 Activity에서 이 기능을

    }

//    이메일 / 닉네임 중복 확인 기능

        fun getRequestDuplCheck(type : String, value : String, handler: JsonResponseHandler?){

//            어디로? + 어떤 데이터? 를 같이 명시하자. type과 value는 query에 담아줘야 하는데 이는 url 주소에 나타내주기 때문에.
//            URL 적으면 + 파라미터 첨부도 같이. => 보조도구(Builder)

            val urlBuilder = "${BASE_URL}/user_check".toHttpUrlOrNull()!!.newBuilder()
//            쿼리에다가 처리하기 좋은 가공된 파라미터를 넣어주는 기능.
            urlBuilder.addEncodedQueryParameter("type", type)
            urlBuilder.addEncodedQueryParameter("value", value)

            val urlString = urlBuilder.build().toString()

            Log.d("완성된 URL", urlString)

            val request = Request.Builder()
                .url(urlString)
//              put,post와 다르게 get으로 뭘 안들고가도 된다. url에 다 담겨있기 때문에!!!!
                .get()
                .build()

            val client = OkHttpClient()

//          request에 들고 서버로 가줘!라는 call을 해준다.
            client.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {
//                  여기까지 하면 ㅈㄴ 이상한 로그캣 찍힘.
                    val bodyString = response.body!!.string()

                    val jsonObj = JSONObject(bodyString)
                    Log.d("응답본문",jsonObj.toString())

                    handler?.onResponse(jsonObj)


                }


            })


        }

//    진행중인 주제 목록 확인 기능 불러오는 함수

        fun getRequestMainInfo(context : Context, handler: JsonResponseHandler?){

//            어디로? + 어떤 데이터? 를 같이 명시하자. type과 value는 query에 담아줘야 하는데 이는 url 주소에 나타내주기 때문에.
//            URL 적으면 + 파라미터 첨부도 같이. => 보조도구(Builder)

        val urlBuilder = "${BASE_URL}/v2/main_info".toHttpUrlOrNull()!!.newBuilder()
//            쿼리에다가 처리하기 좋은 가공된 파라미터를 넣어주는 기능.
//        urlBuilder.addEncodedQueryParameter("type", type)
//        urlBuilder.addEncodedQueryParameter("value", value)

        val urlString = urlBuilder.build().toString()

        Log.d("완성된 URL", urlString)

        val request = Request.Builder()
            .url(urlString)
//              put,post와 다르게 get으로 뭘 안들고가도 된다. url에 다 담겨있기 때문에!!!!
            .get()
            .header("X-Http-Token", ContextUtil.getToken(context)) // X-Http-Token에 토큰을 header로 저장하자.
            .build()

        val client = OkHttpClient()

//          request에 들고 서버로 가줘!라는 call을 해준다.
        client.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {
//                  여기까지 하면 ㅈㄴ 이상한 로그캣 찍힘.
                val bodyString = response.body!!.string()

                val jsonObj = JSONObject(bodyString)
                Log.d("응답본문",jsonObj.toString())

                handler?.onResponse(jsonObj)


            }


        })


    }

//    원하는 주제 상세현황 확인 가능
        fun getRequestTopicDetail(context : Context, topicId : Int, handler: JsonResponseHandler?){

//            어디로? + 어떤 데이터? 를 같이 명시하자. type과 value는 query에 담아줘야 하는데 이는 url 주소에 나타내주기 때문에.
//            URL 적으면 + 파라미터 첨부도 같이. => 보조도구(Builder)

        val urlBuilder = "${BASE_URL}/topic".toHttpUrlOrNull()!!.newBuilder()
//        /몇번 등을 표현하기 위한 기능으로 addEncodedPathSegment가 있다. 여긴 String만 가능하므로 끝에 String으로 가공해주자.
        urlBuilder.addEncodedPathSegment(topicId.toString())
//            쿼리에다가 처리하기 좋은 가공된 파라미터를 넣어주는 기능.
        urlBuilder.addEncodedQueryParameter("order_type", "NEW")
//        urlBuilder.addEncodedQueryParameter("value", value)

        val urlString = urlBuilder.build().toString()

        Log.d("완성된 URL", urlString)

        val request = Request.Builder()
            .url(urlString)
//              put,post와 다르게 get으로 뭘 안들고가도 된다. url에 다 담겨있기 때문에!!!!
            .get()
            .header("X-Http-Token", ContextUtil.getToken(context)) // X-Http-Token에 토큰을 header로 저장하자.
            .build()

        val client = OkHttpClient()

//          request에 들고 서버로 가줘!라는 call을 해준다.
        client.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {
//                  여기까지 하면 ㅈㄴ 이상한 로그캣 찍힘.
                val bodyString = response.body!!.string()

                val jsonObj = JSONObject(bodyString)
                Log.d("응답본문",jsonObj.toString())

                handler?.onResponse(jsonObj)


            }


        })


    }

//    특정 진영에 투표하기
        fun postRequestVote(context : Context, sideId : Int, handler : JsonResponseHandler?){

        val urlString = "${BASE_URL}/topic_vote"

        val formdata = FormBody.Builder()
            .add("side_id",sideId.toString())
            .build()


//          어디로 => 어떻게 => 어떤 데이터를 들고 가는지를 모두 종합해둔, Request 변수 생성.
        val request = Request.Builder()
//                먼저 어디로 갈꺼니?
            .url(urlString)
//                어떤 방식으로 뭘 들고 갈꺼니?
            .post(formdata)
            .header("X-Http-Token",ContextUtil.getToken(context))
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
                val jsonObj = JSONObject(bodyString)

                Log.d("응답본문",jsonObj.toString())

//                    handler 변수가 null이 아니라, (실체가 있다면)
//                    그 내부에 적힌 내용 실행.

                handler?.onResponse(jsonObj)

            }


        })

//      여기까지 하면 ServerUtil -> API 서버 의 구현은 끝난거임. 아직 Activity에서 이 기능을

    }

//    의견 등록하기
        fun postRequestReply(context : Context, topicId : Int, content:String, handler : JsonResponseHandler?){

        val urlString = "${BASE_URL}/topic_reply"

        val formdata = FormBody.Builder()
            .add("topic_id",topicId.toString())
            .add("content",content)
            .build()


//          어디로 => 어떻게 => 어떤 데이터를 들고 가는지를 모두 종합해둔, Request 변수 생성.
        val request = Request.Builder()
//                먼저 어디로 갈꺼니?
            .url(urlString)
//                어떤 방식으로 뭘 들고 갈꺼니?
            .post(formdata)
            .header("X-Http-Token",ContextUtil.getToken(context))
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
                val jsonObj = JSONObject(bodyString)

                Log.d("응답본문",jsonObj.toString())

//                    handler 변수가 null이 아니라, (실체가 있다면)
//                    그 내부에 적힌 내용 실행.

                handler?.onResponse(jsonObj)

            }


        })

//      여기까지 하면 ServerUtil -> API 서버 의 구현은 끝난거임. 아직 Activity에서 이 기능을

    }

}

}