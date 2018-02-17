package com.satoru.kishi.intentservice

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var receiver: ResponseReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val msgIntent = Intent(this, MinhaIntentService::class.java)
        msgIntent.putExtra(MinhaIntentService.PARAM_IN_MSG, "Agora: ")
        startService(msgIntent)

        registrarReceiver()
    }

    private fun registrarReceiver(){
        val filter = IntentFilter("com.satoru.kishi.intentservice.action.RESPONSE")
        filter.addCategory(Intent.CATEGORY_DEFAULT)
        receiver = ResponseReceiver()
        registerReceiver(receiver, filter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver)
    }

    inner class ResponseReceiver: BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val text = intent.getStringExtra(MinhaIntentService.PARAM_OUT_MSG)
            tvResult.text = text
        }

    }

}
