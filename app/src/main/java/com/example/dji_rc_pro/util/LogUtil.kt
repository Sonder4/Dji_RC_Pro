package com.example.dji_rc_pro.util

import timber.log.Timber

/**
 * Unified logging utility using Timber
 * Provides consistent logging across the application
 */
object LogUtil {
    const val DEFAULT_TAG = "DjiRCPro"
    @JvmStatic
    var customTag: String = DEFAULT_TAG
        private set

    fun setTag(tag: String) { customTag = tag }
    fun resetTag() { customTag = DEFAULT_TAG }

    @JvmStatic
    fun d(msg: String, tag: String = customTag) = Timber.tag(tag).d(msg)

    @JvmStatic
    fun d(msg: String, throwable: Throwable, tag: String = customTag) = Timber.tag(tag).d(throwable, msg)

    @JvmStatic
    fun i(msg: String, tag: String = customTag) = Timber.tag(tag).i(msg)

    @JvmStatic
    fun i(msg: String, throwable: Throwable, tag: String = customTag) = Timber.tag(tag).i(throwable, msg)

    @JvmStatic
    fun w(msg: String, tag: String = customTag) = Timber.tag(tag).w(msg)

    @JvmStatic
    fun w(msg: String, throwable: Throwable, tag: String = customTag) = Timber.tag(tag).w(throwable, msg)

    @JvmStatic
    fun w(throwable: Throwable, tag: String = customTag) = Timber.tag(tag).w(throwable)

    @JvmStatic
    fun e(msg: String, tag: String = customTag) = Timber.tag(tag).e(msg)

    @JvmStatic
    fun e(msg: String, throwable: Throwable, tag: String = customTag) = Timber.tag(tag).e(throwable, msg)

    @JvmStatic
    fun e(throwable: Throwable, tag: String = customTag) = Timber.tag(tag).e(throwable)

    @JvmStatic
    fun v(msg: String, tag: String = customTag) = Timber.tag(tag).v(msg)

    @JvmStatic
    fun v(msg: String, throwable: Throwable, tag: String = customTag) = Timber.tag(tag).v(throwable, msg)

    /**
     * Measure execution time of a block
     */
    inline fun <R> measureTime(tag: String = customTag, blockName: String = "Block", block: () -> R): R {
        val start = System.currentTimeMillis()
        return try {
            block()
        } finally {
            val duration = System.currentTimeMillis() - start
            d("$blockName executed in ${duration}ms", tag)
        }
    }

    /**
     * Measure execution time of a suspend block
     */
    suspend inline fun <R> measureTimeAsync(tag: String = customTag, blockName: String = "Block", crossinline block: suspend () -> R): R {
        val start = System.currentTimeMillis()
        return try {
            block()
        } finally {
            val duration = System.currentTimeMillis() - start
            d("$blockName executed in ${duration}ms", tag)
        }
    }
}

/**
 * Extension functions for convenient logging
 */
fun <T> T.logD(tag: String = LogUtil.DEFAULT_TAG, prefix: String = ""): T = apply {
    LogUtil.d("$prefix$this", tag)
}

fun <T> T.logI(tag: String = LogUtil.DEFAULT_TAG, prefix: String = ""): T = apply {
    LogUtil.i("$prefix$this", tag)
}

fun <T> T.logW(tag: String = LogUtil.DEFAULT_TAG, prefix: String = ""): T = apply {
    LogUtil.w("$prefix$this", tag)
}

fun <T> T.logE(tag: String = LogUtil.DEFAULT_TAG, prefix: String = ""): T = apply {
    LogUtil.e("$prefix$this", tag)
}
