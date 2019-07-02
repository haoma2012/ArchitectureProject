package com.mobike.android_architecture

import android.Manifest
import android.content.ComponentName
import android.content.Intent
import android.content.IntentFilter
import android.content.ServiceConnection
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.os.PersistableBundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.text.method.LinkMovementMethod
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import com.mobike.android_architecture.kotlin.KotlinMainActivity
import com.mobike.android_architecture.service.MyService
import com.mobike.android_architecture.utils.EcoStringUtils
import com.mobike.android_architecture.utils.PermissionUtils
import com.mobike.framework.rx.TestRxJavaDemo
import com.mobike.uilibrary.component.MyBroadCastReceived
import com.mobike.uilibrary.widget.UIWidgetActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    val tag = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        }
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val mStrData =
            "<strong><span style='color:#323232;font-size:14px;'>好友为你推荐了<span style='color:#FF384F;font-size:18px;'>17款<span style='color:#323232;font-size:14px;'>商品</span> </span> </span></strong>"
        val htmlStr =
            "<strong><span style='color:#FF384F;font-size:14px;'>好友为你推荐了<span style='color:#FF384F;font-size:18px;'>" + 18 + "款<span style='color:#FF384F;font-size:14px;'>商品</span> </span> </span></strong>"

        val mTvTestHtml = findViewById<TextView>(R.id.tv_test_html)

        val spanned = EcoStringUtils.getHtmlSpanned(mStrData)
        mTvTestHtml.text = spanned
        mTvTestHtml.movementMethod = LinkMovementMethod.getInstance()


        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        // 直接使用id对控件操作：静态布局导入
        btn_widget.setOnClickListener {
            UIWidgetActivity.enterActivity(this)
            // 默认转场动画
            //overridePendingTransition(R.anim.bottom_anim_in, R.anim.bottom_anim_out)
            // 5.0新转场动画
        }

        val hasNet = PermissionUtils.hasPermission(applicationContext, Manifest.permission.INTERNET)

        Toast.makeText(applicationContext, """是否禁止网络权限：$hasNet""", Toast.LENGTH_LONG).show()
        Log.d(tag, """是否禁止网络权限：$hasNet""")
        // 打卡wifi设置
        //startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
        // 打卡无线和网络页面
        //startActivity(Intent(Settings.ACTION_WIRELESS_SETTINGS))
        // 打卡wifi设置
        //startActivity(Intent(Settings.ACTION_APPLICATION_SETTINGS))


        btn_kotlin.setOnClickListener {
            val kotlinMainActivity = KotlinMainActivity()
            kotlinMainActivity.enterToActivity(this)
        }

        // 初始化服务
        initService()
        // 初始化广播
        initBroadCast()

        initRxJava()
    }

    /**
     * 初始化使用RxJava
     */
    private fun initRxJava() {
        var instance = TestRxJavaDemo.getInstance()

        instance.realUseRxJava()

        // 获取金山词霸网络返回数据
        com.mobike.uilibrary.http.RetrofitHttpUtils.getInstance().getChannelBrandList()
        //com.mobike.uilibrary.http.RetrofitHttpUtils.getInstance().doRequestByRetrofit()
    }

    private var mMyBroadCastReceived: MyBroadCastReceived? = null
    private fun initBroadCast() {
        mMyBroadCastReceived = MyBroadCastReceived()
        val intentFilter = IntentFilter()

        // 2. 设置接收广播的类型
        intentFilter.addAction(MyBroadCastReceived.ACTION_BROADCAST)
        registerReceiver(mMyBroadCastReceived, intentFilter)
    }


    private fun initService() {
        var isStart = false
        var isBinder = false
        btn_start_service.setOnClickListener {
            if (!isStart) {
                isStart = true
                btn_start_service.text = "关闭服务"
                startService(Intent(this, MyService::class.java))
            } else {
                isStart = false
                btn_start_service.text = "启动服务"
                //构建停止服务的Intent对象
                //调用stopService()方法-传入Intent对象,以此停止服务
                stopService(Intent(this, MyService::class.java))
            }
        }

        btn_bind_service.setOnClickListener {
            if (!isBinder) {
                isBinder = true
                btn_bind_service.text = "解绑服务"
                val bindIntent = Intent(this, MyService::class.java)

                bindService(bindIntent, connection, BIND_AUTO_CREATE)
            } else {
                isBinder = false
                btn_bind_service.text = "绑定服务"
                unbindService(connection)
            }


        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private var myBinder: MyService.MyBinder? = null
    //创建ServiceConnection的匿名类
    private val connection = object : ServiceConnection {

        //重写onServiceConnected()方法和onServiceDisconnected()方法
        //在Activity与Service建立关联和解除关联的时候调用

        override fun onServiceDisconnected(name: ComponentName) {}

        //在Activity与Service解除关联的时候调用
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            //实例化Service的内部类myBinder
            //通过向下转型得到了MyBinder的实例
            myBinder = service as MyService.MyBinder
            //在Activity调用Service类的方法
            myBinder!!.service_connect_Activity()
        }
    }


    /**
     * 查看AspectJ是否Hook进来
     */
    override fun onStart() {
        super.onStart()
        Log.d(tag, "MainActivity: onStart ")
    }


    override fun onResume() {
        super.onResume()
        Log.d(tag, "MainActivity: onResume ")

    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        Log.d(tag, "MainActivity: onSaveInstanceState ")

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(tag, "MainActivity: onRestoreInstanceState ")

    }

    override fun onPause() {
        super.onPause()
        Log.d(tag, "MainActivity: onPause ")

    }

    override fun onStop() {
        super.onStop()
        Log.d(tag, "MainActivity: onStop ")

    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "MainActivity: onDestroy ")
        if (mMyBroadCastReceived != null) {
            unregisterReceiver(mMyBroadCastReceived)
        }
    }
}
