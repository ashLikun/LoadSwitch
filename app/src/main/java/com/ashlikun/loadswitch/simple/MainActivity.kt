package com.ashlikun.loadswitch.simple

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ashlikun.loadswitch.*

class MainActivity : AppCompatActivity(), OnLoadSwitchClick {
    var loadSwitchService: LoadSwitchService? = null
    var currStatus = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        LoadSwitch.init(application)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadSwitchService = LoadSwitch().create(findViewById(R.id.twoRecyclerView), DefaultOnLoadLayoutListener(this, this))
        findViewById<View>(R.id.button).setOnClickListener { v: View? ->
            if (currStatus == 0) {
                loadSwitchService!!.showEmpty(ContextData(imgHeight = 500, imgWidth = 500))
                currStatus = 1
            } else if (currStatus == 1) {
                loadSwitchService!!.showLoading(ContextData())
                currStatus = 2
            } else if (currStatus == 2) {
                loadSwitchService!!.showContent()
                currStatus = 3
            } else if (currStatus == 3) {
                loadSwitchService!!.showRetry(ContextData())
                currStatus = 0
            }
        }
        findViewById<View>(R.id.button2).setOnClickListener { startActivity(Intent(this@MainActivity, Main2Activity::class.java)) }
    }

    override fun onRetryClick(data: ContextData) {}
    override fun onEmptyClick(data: ContextData) {}
}