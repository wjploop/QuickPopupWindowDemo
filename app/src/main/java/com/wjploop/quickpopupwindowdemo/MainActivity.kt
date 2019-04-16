package com.wjploop.quickpopupwindowdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.PopupWindow
import android.widget.Toast
import com.wjploop.Param
import com.wjploop.showDialog
import com.wjploop.showPopup
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        show.setOnClickListener {
            showPopup(parent = root,
                title = "温馨提示",
                content = "需要钱啊哈",
                actions = *arrayOf(
                Pair(Param("photo","photo"),this@MainActivity::takePhoto)
            ))
        }

        showAlert.setOnClickListener {
            showDialog("提示","红包来了",pos = Pair(Param("obj","去领红包"),this@MainActivity::getRedPacket))
        }
    }

    fun getRedPacket(params: Param) {
        Toast.makeText(this,"获得5毛:${params.param}",Toast.LENGTH_SHORT).show()
    }


    fun takePhoto(params: Param, popupWindow: PopupWindow):Unit {
        Toast.makeText(this,"take photo",Toast.LENGTH_SHORT).show()
    }

    fun gallery(param: String, popupWindow: PopupWindow) {
        Toast.makeText(this,"from gallery",Toast.LENGTH_SHORT).show()
    }

}
