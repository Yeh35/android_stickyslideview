package com.yeh35.android.stickyslideviewexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.yeh35.android.stickyslideview.OnTopClickListener
import com.yeh35.android.stickyslideview.StickySlideView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var stickySlideViewXml: StickySlideView
    private lateinit var stickySlideViewCode: StickySlideView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        stickySlideViewXml = findViewById(R.id.sticky_slide_view_xml)

        val btnCodeShow: Button = findViewById(R.id.btn_code_show)
        val btnXmlShow: Button = findViewById(R.id.btn_xml_show)

        stickySlideViewCode = StickySlideView(this)
        stickySlideViewCode.addView(View(this), ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            5000
        ))

        this.addContentView(stickySlideViewCode, ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        ))

        btnCodeShow.setOnClickListener(this)
        btnXmlShow.setOnClickListener(this)

        stickySlideViewCode.onTopClickListener = object : OnTopClickListener {
            override fun onTopClick(v: StickySlideView) {
                stickySlideViewCode.close()
            }
        }
    }

    override fun onClick(v: View) {
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