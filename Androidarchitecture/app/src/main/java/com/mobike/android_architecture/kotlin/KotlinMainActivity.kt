package com.mobike.android_architecture.kotlin

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import com.mobike.android_architecture.R
import com.mobike.uilibrary.adapter.BaseTextRecycleAdapter
import com.mobike.uilibrary.base.BaseActivity
import com.mobike.uilibrary.model.DetailItemModel
import kotlinx.android.synthetic.main.activity_main_kotlin.*
import org.jetbrains.anko.*
import java.net.URL

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019-04-30 11:32
 */
class KotlinMainActivity : BaseActivity() {
    private val TAG: String = "KotlinMainActivity"
    private var mRecycleView: RecyclerView? = null
    private var mAdapter: BaseTextRecycleAdapter? = null
    private var mTvContent: TextView? = null
    private var isLogin: Boolean by Preference(this, "isLogin", false)

    fun enterToActivity(context: Context) {
        val intent = Intent()
        intent.setClass(context, KotlinMainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        // 5.0新增转场动画
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(context as Activity).toBundle())
        } else {
            context.startActivity(intent)
        }
    }

    // 使用AnKo 构建Intent
    fun enterActivity(str: String) {
        startActivity<KotlinMainActivity>(
            "id" to 5, "city" to "china"
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //
        Log.d(TAG, " kotlin Main Activity onCreate ")
    }


    override fun getLayoutId(): Int {
        return R.layout.activity_main_kotlin
    }


    override fun initView() {

        val recyclerView: RecyclerView = find(R.id.recycle_view)

        mRecycleView = findViewById(R.id.recycle_view)
        // 设置布局管理器
        mRecycleView!!.layoutManager = LinearLayoutManager(this)
        // 设置分割线
        val itemDecoration = DividerItemDecoration(
            this, DividerItemDecoration.VERTICAL
        )
        itemDecoration.setDrawable(resources.getDrawable(com.mobike.uilibrary.R.drawable.gray_round_1))
        mRecycleView!!.addItemDecoration(itemDecoration)

        // 设置数据适配器
        if (mAdapter == null) {
            mAdapter = BaseTextRecycleAdapter(this, getDialogListData())
        }
        mRecycleView!!.adapter = mAdapter


        tv_kotlin.setOnClickListener {
            toast("点击学习Kotlin", Toast.LENGTH_SHORT)
            templantToast("Hello World!")

        }

        // 使用AnKo替换布局
        testAnKoMethod()
    }

    private fun getDialogListData(): List<DetailItemModel> {
        val list = ArrayList<DetailItemModel>()

        val model = DetailItemModel()
        model.name = "普通弹框"

        val model1 = DetailItemModel()
        model.name = "单选弹框"

        val model2 = DetailItemModel()
        model2.name = "多选弹框"

        val model3 = DetailItemModel()
        model3.name = "输入框弹框"

        val model4 = DetailItemModel()
        model4.name = "列表弹框"

        val model5 = DetailItemModel()
        model5.name = "自定义View弹框"

        val model6 = DetailItemModel()
        model6.name = "显示PopupWindow"

        val model7 = DetailItemModel()
        model7.name = "显示自定义CustomPopWindow"

        list.add(model)
        list.add(model1)
        list.add(model2)
        list.add(model3)
        list.add(model4)
        list.add(model5)
        list.add(model6)
        list.add(model7)

        return list

    }


    /**
     * 调用Toast
     */
    fun toast(message: String, length: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, length).show()
    }

    /**
     * 调用扩展函数调用Toast
     */
    fun Context.templantToast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }


    /**
     * 框架AnKo使用
     */
    private fun testAnKoMethod() {

        // 加载布局
        scrollView {
            verticalLayout {
                val inputName = editText("请输入用户名")

                button("登录").setOnClickListener {
                    toast("hello ${inputName.text}")

                    toast("Hi AnKo")

//                    alert("Hi, I'm Roy", "Have you tried turning it off and on again?") {
//                        yesButton { toast("Oh…") }
//                        noButton {}
//                    }.show()

                    // alert(Appcompat, "Some text message").show()
                }

                button {
                    text = "线程切换"
                    textColor = resources.getColor(R.color.white)
                    textSize = 16f
                    gravity = Gravity.CENTER
                    backgroundColor = resources.getColor(R.color.red_b)
                }.setOnClickListener {
                    getNetDataFromHttp()
                }

                mTvContent = textView {
                    text = "我是网页内容"
                    textSize = 13f
                    textColor = resources.getColor(R.color.black_66)
                    height = resources.getDimensionPixelOffset(R.dimen.dp_value_50)
                    width = resources.getDimensionPixelOffset(R.dimen.dp_150)
                }
            }


        }
        testSPFile()

    }


    private fun testSPFile() {
        isLogin = if (isLogin) {
            println("isLogin $isLogin")
            false
        } else {
            println("isLogin $isLogin")
            true
        }
    }

    private fun showBottomSheet() {

        // Builder 版本

    }


    private fun getNetDataFromHttp() {
        doAsync {
            val response = URL("http://www.baidu.com").readText()

            uiThread {
                mTvContent!!.text = response
                Log.d(TAG, response)
            }
        }
    }

    /**
     * 测试集合
     */
    private fun testCollection() {
        val list = arrayOf(1, 2, 3, 4, 5, 6)
        list.forEach {

        }

        if (list.contains(1)) {
            println("has item 1")
        }

        val value = list.elementAt(0)
        println("has item $value")


        val sb = StringBuffer("abc")


    }


}