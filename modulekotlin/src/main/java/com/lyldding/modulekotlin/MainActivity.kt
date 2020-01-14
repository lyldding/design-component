package com.lyldding.modulekotlin

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.lyldding.commonlib.Constants
import com.lyldding.commonlib.utils.MainLooper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.*
import okio.ByteString
import java.util.concurrent.TimeUnit

@Route(path = Constants.GROUP_KOTLIN + "main")
class MainActivity : AppCompatActivity() {
    var count = 1
    lateinit var mWebsocket: WebSocket
    lateinit var mFloatScreenView: FloatScreenView

    private val run = Runnable {
        addFloatView()
        startLoop()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kotlin_activity_main)
        mFloatScreenView = findViewById(R.id.float_view)
        startLoop()
//        GlobalScope.launch(Dispatchers.IO) {
//            println("@@@@@@@@@@ 11111111")
//            delay(100)
//            println("@@@@@@@@@@ 2222222")
//        }
//
//        while (true) {
//            println("@@@@@@@@@@ ${count.inc()}")
//        }
//        openWebSocket()
    }

    private fun startLoop() {
        MainLooper.getInstance().postDelayed(
            run, 300
        )
    }

    private fun addFloatView() {
        val textView = TextView(this)
        val text = "count = ${count++}"
        textView.text = text
        mFloatScreenView.addFloatView(textView)

        val image = ImageView(this)
        image.layoutParams = ViewGroup.LayoutParams(100, 50)
        image.setImageDrawable(
            ColorDrawable(
                when (count % 3) {
                    0 -> Color.RED
                    1 -> Color.BLUE
                    2 -> Color.BLACK
                    else -> Color.DKGRAY
                }
            )
        )
        mFloatScreenView.addFloatView(image)
    }


    private fun openWebSocket() {
        val okHttp = OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
            .pingInterval(5, TimeUnit.SECONDS)
            .build()
        val request = Request.Builder().url("ws://echo.websocket.org")
            .build()
        println("@@@@@@@ port = ${request.url().port()}")
        mWebsocket = okHttp.newWebSocket(request, object : WebSocketListener() {

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                super.onFailure(webSocket, t, response)
                println("@@@@@@@ onFailure = ${t.message} ,response  = ${response?.toString()}")
            }

            override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
                super.onMessage(webSocket, bytes)
                println("@@@@@@@ onMessage = $bytes")
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                super.onMessage(webSocket, text)
                println("@@@@@@@ onMessage = $text")
            }

            override fun onOpen(webSocket: WebSocket, response: Response) {
                super.onOpen(webSocket, response)
                println("@@@@@@@ onOpen")
                GlobalScope.launch {
                    while (true) {
                        mWebsocket.send("hello test ${count++}")
                        delay(100)
                    }
                }
            }

            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                super.onClosed(webSocket, code, reason)
                println("@@@@@@@ onClosed $code , $reason")
            }

            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                super.onClosing(webSocket, code, reason)
                println("@@@@@@@ onClosing  $code , $reason")
            }
        })
    }
}
