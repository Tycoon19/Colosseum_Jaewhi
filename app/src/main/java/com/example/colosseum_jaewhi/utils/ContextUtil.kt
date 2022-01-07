package com.example.colosseum_jaewhi.utils

import android.content.Context

class ContextUtil {

    companion object{

        private val prefName = "ColosseumPref"

        private val TOKEN = "TOKEN"

        fun setToken(context : Context, token : String){

            val pref = context.getSharedPreferences(prefName,Context.MODE_PRIVATE)
//            메모장의 edit모드에서 데이터를 추가한다. 실제로 저장할 값은 token이다. apply()로 저장까지 해줘.
            pref.edit().putString(TOKEN,token).apply()
        }

        fun getToken(context: Context) : String {

            val pref = context.getSharedPreferences(prefName,Context.MODE_PRIVATE)
//            메모장에 있는 TOKEN값을 반환해주고 만약 없다면 ""을 반환해줘. 하지만 절대 null값이 없을거야ㅋ라는 의미에서 !! 붙여줌ㅋㅋ
            return pref.getString(TOKEN,"")!!

        }



    }

}