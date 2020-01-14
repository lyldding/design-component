package com.lyldding.modulekotlin

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import com.lyldding.commonlib.utils.LogUtils
import com.lyldding.commonlib.utils.SizeUtils
import kotlin.random.Random

/**
 * 飘屏view
 * @author https://github.com/lyldding
 */
class FloatScreenView : FrameLayout {
    private val times = arrayOf(3500, 4000, 4500, 5000, 5500)
    private val mRandom: Random = Random
    private var mIsSingleLine: Boolean = false
    private var mIsSingleTime: Boolean = false

    constructor(context: Context) : super(context)

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    )


    fun addFloatView(view: View) {
        if (measuredHeight <= 0 || measuredWidth <= 0) {
            LogUtils.e("还未初始化")
            return
        }
        if (view.layoutParams == null) {
            view.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }

        val viewSize = SizeUtils.measureView(view)
        val viewHeight = viewSize[1]
        val viewWidth = viewSize[0]

        if (viewHeight > measuredHeight) {
            LogUtils.e("viewHeight > measuredHeight : viewHeight = $viewHeight , measuredHeight = $measuredHeight")
            return
        }

        val top = getViewTop(viewHeight)
        val animX =
            ObjectAnimator.ofFloat(
                view,
                "translationX",
                measuredWidth.toFloat(),
                -viewWidth.toFloat()
            )
        val animY = ObjectAnimator.ofFloat(view, "translationY", top.toFloat(), top.toFloat())
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(animX, animY)
        animatorSet.interpolator = LinearInterpolator()
        val duration = times[if (mIsSingleTime) 0 else mRandom.nextInt(times.size)]
        animatorSet.duration = (duration + duration * viewWidth / measuredWidth).toLong()
        animatorSet.addListener(object : Animator.AnimatorListener {
            override fun onAnimationCancel(animation: Animator?) {
                removeView(view)
            }

            override fun onAnimationEnd(animation: Animator?) {
                removeView(view)
            }

            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }
        })

        if (indexOfChild(view) != -1) {
            removeView(view)
        }
        addView(view)
        animatorSet.start()
    }

    private fun getViewTop(viewHeight: Int): Int {
        var top = 0
        if (mIsSingleLine) {
            top = (measuredHeight - viewHeight) / 2
        } else {
            val lines = measuredHeight / viewHeight
            top = if (lines > 1) (measuredHeight % viewHeight) / 2 else 0
            val lineIndex = mRandom.nextInt(lines)
            top += lineIndex * viewHeight
        }

        return top
    }

    /**
     * @param isSingleLine true 单行
     */
    fun setSingleLine(isSingleLine: Boolean) {
        mIsSingleLine = isSingleLine
    }

    /**
     * @param isSingleTime true 时间固定
     */
    fun setSingleTime(isSingleTime: Boolean) {
        mIsSingleTime = isSingleTime
    }

}