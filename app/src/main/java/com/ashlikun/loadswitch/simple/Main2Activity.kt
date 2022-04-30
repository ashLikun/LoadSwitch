package com.ashlikun.loadswitch.simple

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ashlikun.loadswitch.*

/**
 * 测试约束布局
 */
class Main2Activity : AppCompatActivity(), OnLoadSwitchClick {
    var loadSwitchService: LoadSwitchService? = null
    var currStatus = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        loadSwitchService = LoadSwitch().create(findViewById(R.id.recycleView), DefaultOnLoadLayoutListener(this, this))
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
    }

    override fun onRetryClick(data: ContextData) {}
    override fun onEmptyClick(data: ContextData) {}
}