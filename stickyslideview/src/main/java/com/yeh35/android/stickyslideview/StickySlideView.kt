package com.yeh35.android.stickyslideview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.constraintlayout.motion.widget.MotionLayout
import com.yeh35.android.stickyslideview.utils.UtilResources

class StickySlideView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

    //properties
    @ColorInt
    var themeBackGroundColor: Int = 0
        set(value) {
            motionLayout.setBackgroundColor(value)
            field = value
        }
    var state: State = State.CLOSE
        private set
    var transitionListener: MotionLayout.TransitionListener? = null
    var duration: Int = 300
        set(value) {
            motionLayout.getTransition(R.id.transition_sticky_slide_swipe).duration = field
            field = value
        }
    var onTopClickListener: OnTopClickListener? = null

    //private
    private val utilResources = UtilResources(this.resources)
    private val imm: InputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    //views
    private val motionLayout: MotionLayout
    private val layoutBase: FrameLayout
    private val viewTop: View

    init {
        // view mapping
        motionLayout = View.inflate(context, R.layout.sticky_slide_view, null) as MotionLayout
        layoutBase = motionLayout.findViewById(R.id.layout_base)
        viewTop = motionLayout.findViewById(R.id.view_top)
        super.addView(motionLayout)

        //properties mappting
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.StickySlideView,
            0,
            0
        ).apply {
            themeBackGroundColor = getColor(
                R.styleable.StickySlideView_themeBackgroundColor,
                utilResources.getColor(R.color.transparent_gray)
            )
            duration = getInteger(R.styleable.StickySlideView_duration, duration)
            this@StickySlideView.visibility = getInt(R.styleable.StickySlideView_android_visibility, View.GONE)
        }

        // Listener setting
        motionLayout.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
                transitionListener?.onTransitionStarted(p0, p1, p2)
            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
                transitionListener?.onTransitionChange(p0, p1, p2, p3)
            }

            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {
                transitionListener?.onTransitionTrigger(p0, p1, p2, p3)
            }

            override fun onTransitionCompleted(motion: MotionLayout?, currentId: Int) {
                if (currentId == R.id.start) {
                    this@StickySlideView.visibility = View.GONE
                    state = State.CLOSE
                } else {
                    state = State.SHOW
                }

                transitionListener?.onTransitionCompleted(motion, currentId)
            }
        })

        viewTop.setOnClickListener {
            onTopClickListener?.onTopClick(this)
        }

        if (this.visibility == View.VISIBLE) {
            this.show()
        }
    }

    override fun addView(child: View?) {
        if (motionLayout == child) {
            super.addView(child)
        } else {
            layoutBase.addView(child)
        }
    }

    override fun addView(child: View?, index: Int) {
        if (motionLayout == child) {
            super.addView(child, index)
        } else {
            layoutBase.addView(child, index)
        }
    }

    override fun addView(child: View?, width: Int, height: Int) {
        if (motionLayout == child) {
            super.addView(child, width, height)
        } else {
            layoutBase.addView(child, width, height)
        }
    }

    override fun addView(child: View?, params: ViewGroup.LayoutParams?) {
        if (motionLayout == child) {
            super.addView(child, params)
        } else {
            layoutBase.addView(child, params)
        }
    }

    override fun addView(child: View?, index: Int, params: ViewGroup.LayoutParams?) {
        if (motionLayout == child) {
            super.addView(child, index, params)
        } else {
            layoutBase.addView(child, index, params)
        }
    }

    override fun setOnClickListener(l: OnClickListener?) {
        this.layoutBase.setOnClickListener(l)
    }

    fun show() {
        imm.hideSoftInputFromWindow(this.windowToken, 0)
        this.visibility = View.VISIBLE
        motionLayout.transitionToEnd()
    }

    fun close() {
        motionLayout.transitionToStart()
    }

    enum class State {
        CLOSE,
        SHOW
    }

}