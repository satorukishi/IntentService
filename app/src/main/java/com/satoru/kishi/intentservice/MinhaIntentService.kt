package com.satoru.kishi.intentservice

import android.app.IntentService
import android.content.Intent
import android.os.SystemClock
import android.text.format.DateFormat

class MinhaIntentService : IntentService("MinhaIntentService") {
    override fun onHandleIntent(intent: Intent?) {
        val msg = intent!!.getStringExtra(PARAM_IN_MSG)
        SystemClock.sleep(3000)
        val resultTxt = (msg + " " + DateFormat.format("MM/dd/yy h:mm:aa", System.currentTimeMillis()))

        val broadcastIntent = Intent()
        broadcastIntent.action = "com.satoru.kishi.intentservice.action.RESPONSE"
        broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT)
        broadcastIntent.putExtra(PARAM_OUT_MSG, resultTxt)
        sendBroadcast(broadcastIntent)
    }

    companion object {
        val PARAM_IN_MSG = "mensagem_entrada"
        val PARAM_OUT_MSG = "mensagem_saida"
    }

}