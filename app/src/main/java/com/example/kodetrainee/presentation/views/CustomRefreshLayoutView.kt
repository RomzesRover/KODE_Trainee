package com.example.kodetrainee.presentation.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.example.kodetrainee.R
import com.google.android.material.progressindicator.CircularProgressIndicator
import me.dkzwm.widget.srl.SmoothRefreshLayout
import me.dkzwm.widget.srl.SmoothRefreshLayout.SR_STATUS_COMPLETE
import me.dkzwm.widget.srl.SmoothRefreshLayout.SR_STATUS_REFRESHING
import me.dkzwm.widget.srl.extra.IRefreshView
import me.dkzwm.widget.srl.extra.IRefreshView.*
import me.dkzwm.widget.srl.indicator.IIndicator

class CustomRefreshLayoutView(context: Context) : FrameLayout(context, null, 0), IRefreshView<IIndicator> {

    private var circleProgressBar: CircularProgressIndicator

    init {
        val viewHeader = LayoutInflater.from(context).inflate(R.layout.custom_resfresh_layout_header, this)
        circleProgressBar = viewHeader.findViewById(R.id.progressBar)
    }

    override fun getType(): Int {
        return TYPE_HEADER.toInt()
    }

    override fun getStyle(): Int {
        return STYLE_DEFAULT.toInt()
    }

    override fun getCustomHeight(): Int {
        return 0
    }

    override fun getView(): View {
        return this
    }

    override fun onFingerUp(layout: SmoothRefreshLayout?, indicator: IIndicator?) { }

    override fun onReset(layout: SmoothRefreshLayout?) { }

    override fun onRefreshPrepare(layout: SmoothRefreshLayout?) { }

    override fun onRefreshBegin(layout: SmoothRefreshLayout?, indicator: IIndicator?) { }

    override fun onRefreshComplete(layout: SmoothRefreshLayout?, isSuccessful: Boolean) { }

    override fun onRefreshPositionChanged(
        layout: SmoothRefreshLayout?,
        status: Byte,
        indicator: IIndicator?
    ) {
        if (status == SR_STATUS_REFRESHING || status == SR_STATUS_COMPLETE){
            circleProgressBar.isIndeterminate = true
        } else {
            indicator?.let {
                val progressToStartRefresh = (it.currentPercentOfRefreshOffset * 100).toInt()

                if (progressToStartRefresh >= 100){
                    circleProgressBar.isIndeterminate = true
                } else {
                    circleProgressBar.isIndeterminate = false
                    circleProgressBar.progress = progressToStartRefresh
                }
            }
        }
    }

    override fun onPureScrollPositionChanged(
        layout: SmoothRefreshLayout?,
        status: Byte,
        indicator: IIndicator?
    ) { }


}