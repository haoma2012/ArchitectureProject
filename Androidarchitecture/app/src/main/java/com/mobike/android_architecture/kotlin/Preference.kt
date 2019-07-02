package com.mobike.android_architecture.kotlin

import android.content.Context
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Preference sp
 * Created by yangdehao@xiaoyouzi.com  on 2019-04-30 17:08
 */
class Preference<T>(private val context: Context, val name: String?, private val default: T) : ReadWriteProperty<Any?, T> {
    private val prefs by lazy {
        context.getSharedPreferences("Androidarchitecture", Context.MODE_PRIVATE)
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T = with(prefs) {
        val res: Any = when (default) {
            is Long -> {
                getLong(name, 0)
            }
            is String -> {
                getString(name, default)
            }
            is Float -> {
                getFloat(name, default)
            }
            is Int -> {
                getInt(name, default)
            }
            is Boolean -> {
                getBoolean(name, default)
            }
            else -> {
                throw IllegalArgumentException("This type can't be saved into Preferences")
            }
        }
        res as T
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) = with(prefs.edit()) {
        when (value) {
            is Long -> putLong(name, value)
            is String -> putString(name, value)
            is Float -> putFloat(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            else -> {
                throw IllegalArgumentException("This type can't be saved into Preferences")
            }
        }.apply()
    }
}