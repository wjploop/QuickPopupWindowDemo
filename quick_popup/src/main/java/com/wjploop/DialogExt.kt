package com.wjploop

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.wjploop.R.id.content


fun Activity.showAlertDialog(
    title: String = "",
    content: String = "",
    pos: Pair<Param, (Param) -> Unit> = Pair(Param("pos","确认"), ::nothinng),
    neg: Pair<Param, (Param) -> Unit> = Pair(Param("neg","取消"), ::nothinng)
) {

    val dialog = AlertDialog.Builder(this).create()

    val root = LayoutInflater.from(this).inflate(R.layout.simple_dialog, null)
    root.findViewById<TextView>(R.id.title).text = title
    root.findViewById<TextView>(R.id.content).text = content

    root.findViewById<TextView>(R.id.pos).apply {
        text = pos.first.actionName
        setOnClickListener {
            pos.second(pos.first)
            dialog.dismiss()
        }
    }
    root.findViewById<TextView>(R.id.neg).apply {
        text = neg.first.actionName
        setOnClickListener {
            neg.second(neg.first)
            dialog.dismiss()
        }
    }


    dialog.apply {
        window.attributes=window.attributes.apply {
            width= (width*0.7).toInt()
        }
    }.show()
    dialog.window.setContentView(root)


}

fun nothinng(param: Param):Unit {

}