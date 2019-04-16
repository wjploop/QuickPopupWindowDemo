package com.wjploop

import android.app.Activity
import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.view.*
import android.widget.Button
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import com.wjploop.R.id.content


fun Activity.showPopup(
    parent: ViewGroup,
    title: String? = null,
    content: String? = null,
    cancelText:String="取消",
    vararg actions: Pair<Param, (Param) -> Unit>
) {
    val popupWindow = PopupWindow(-1, -2)
    popupWindow.apply {
        width = WindowManager.LayoutParams.MATCH_PARENT
        height = WindowManager.LayoutParams.WRAP_CONTENT
        contentView = LayoutInflater.from(this@showPopup).inflate(R.layout.popup_parent, null, false)
        contentView.findViewById<Button>(R.id.cancel).apply {
            text=cancelText
            setOnClickListener {
                popupWindow.dismiss()
            }
        }


        for ((any, action) in actions) {
            //You cannot clone views, the way to do it is to inflate your View every time.
            //Note that the XML is compiled into binary which can be parsed very efficiently.
            val itemView = LayoutInflater.from(this@showPopup).inflate(R.layout.item, null, false)

            itemView.apply {
                findViewById<Button>(R.id.action_name).apply {
                    text = any.actionName
                    setOnClickListener {
                        action(any)
                        popupWindow.dismiss()
                    }
                }
            }
            contentView.findViewById<LinearLayout>(R.id.container).addView(itemView, 0)
        }
        animationStyle = R.style.popup_window_animation
        isOutsideTouchable = true
        isFocusable = true
        setBackgroundDrawable(BitmapDrawable())

        setOnDismissListener {
            this@showPopup.window.attributes = this@showPopup.window.attributes.apply {
                alpha = 1.0f
            }
        }
        //设置title content
        if (!title.isNullOrBlank()) {
            contentView.findViewById<TextView>(R.id.title).apply {
                visibility=View.VISIBLE
                text=title
            }

        }

        if (!content.isNullOrBlank()) {
            contentView.findViewById<TextView>(R.id.content).apply {
                visibility=View.VISIBLE
                text=content
            }
        }
        //显示一个间隔
        if (!(content.isNullOrBlank() || title.isNullOrBlank())) {
            contentView.findViewById<View>(R.id.divider).apply {
                visibility=View.VISIBLE
            }
        }
    }

    window.attributes = (window.attributes.apply {
        alpha = 0.7f

    })

    popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0)

}