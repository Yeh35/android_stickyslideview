package com.yeh35.android.stickyslideviewexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.yeh35.android.stickyslideview.StickySlideView

class MainActivity : AppCompatActivity() {

    private lateinit var stickySlideViewXml: StickySlideView
    private lateinit var stickySlideViewCode: StickySlideView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        stickySlideViewXml = findViewById(R.id.sticky_slide_view_xml)

        stickySlideViewCode = StickySlideView(this)
        stickySlideViewCode.addView(View(this), ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            500
        ))
        this.addContentView(stickySlideViewCode, ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        ))
    }

    fun onClick(v: View) {
        when (v.id) {
            R.id.btn_code_show -> {
                stickySlideViewCode.show()
            }
            R.id.btn_xml_show -> {
                stickySlideViewXml.show()
            }
        }
    }
}