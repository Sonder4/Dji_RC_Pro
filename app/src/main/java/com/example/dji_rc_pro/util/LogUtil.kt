package com.example.dji_rc_pro.util

import android.util.Log
import timber.log.Timber

object LogUtil {
    const val DEFAULT_TAG = "DjiRCPro"
    private var customTag: String = DEFAULT_TAG

    fun setTag(tag: String) {
        customTag = tag
    }

    fun resetTag() {
        customTag = DEFAULT_TAG
    }

    fun d(message: String, tag: String = customTag) {
        Timber.d(tag, message)
    }

    fun d(message: String, vararg args: Any?, tag: String = customTag) {
        Timber.d(tag, message, *args)
    }

    fun i(message: String, tag: String = customTag) {
        Timber.i(tag, message)
    }

    fun i(message: String, vararg args: Any?, tag: String = customTag) {
        Timber.i(tag, message, *args)
    }

    fun w(message: String, tag: String = customTag) {
        Timber.w(tag, message)
    }

    fun w(message: String, vararg args: Any?, tag: String = customTag) {
        Timber.w(tag, message, *args)
    }

    fun w(throwable: Throwable, message: String? = null, tag: String = customTag) {
        if (message != null) {
            Timber.w(throwable, tag, message)
        } else {
            Timber.w(throwable, tag, throwable.message ?: "Unknown exception")
        }
    }

    fun e(message: String, tag: String = customTag) {
        Timber.e(tag, message)
    }

    fun e(message: String, vararg args: Any?, tag: String = customTag) {
        Timber.e(tag, message, *args)
    }

    fun e(throwable: Throwable, message: String? = null, tag: String = customTag) {
        if (message != null) {
            Timber.e(throwable, tag, message)
        } else {
            Timber.e(throwable, tag, throwable.message ?: "Unknown exception")
        }
    }

    fun wtf(message: String, tag: String = customTag) {
        Timber.wtf(tag, message)
    }

    fun wtf(throwable: Throwable, message: String? = null, tag: String = customTag) {
        if (message != null) {
            Timber.wtf(throwable, tag, message)
        } else {
            Timber.wtf(throwable, tag, throwable.message ?: "Unknown fatal exception")
        }
    }

    fun v(message: String, tag: String = customTag) {
        Timber.v(tag, message)
    }

    fun v(message: String, vararg args: Any?, tag: String = customTag) {
        Timber.v(tag, message, *args)
    }

    fun log(priority: Int, message: String, tag: String = customTag) {
        Timber.log(priority, tag, message)
    }

    fun log(priority: Int, message: String, vararg args: Any?, tag: String = customTag) {
        Timber.log(priority, tag, message, *args)
    }

    fun log(priority: Int, throwable: Throwable, message: String? = null, tag: String = customTag) {
        if (message != null) {
            Timber.log(priority, throwable, tag, message)
        } else {
            Timber.log(priority, throwable, tag, throwable.message ?: "Unknown exception")
        }
    }

    fun enterMethod(tag: String = customTag) {
        Timber.d(tag, "Enter method: ${getMethodName()}")
    }

    fun exitMethod(tag: String = customTag) {
        Timber.d(tag, "Exit method: ${getMethodName()}")
    }

    fun methodFlow(message: String, tag: String = customTag) {
        Timber.d(tag, "[FLOW] $message - ${getMethodName()}")
    }

    private fun getMethodName(): String {
        val stackTrace = Thread.currentThread().stackTrace
        return stackTrace.getOrNull(4)?.let { element ->
            "${element.className}.${element.methodName}()"
        } ?: "Unknown"
    }

    fun performance(tag: String = customTag, block: () -> Unit): Long {
        val startTime = System.nanoTime()
        block()
        val duration = (System.nanoTime() - startTime) / 1_000_000
        Timber.d(tag, "Performance: ${duration}ms")
        return duration
    }

    suspend fun performanceAsync(tag: String = customTag, block: suspend () -> Unit): Long {
        val startTime = System.nanoTime()
        block()
        val duration = (System.nanoTime() - startTime) / 1_000_000
        Timber.d(tag, "Performance: ${duration}ms")
        return duration
    }

    fun benchmark(name: String, iterations: Int = 1000, tag: String = customTag, block: () -> Unit) {
        val warmup = 10
        repeat(warmup) { block() }

        val times = mutableListOf<Long>()
        repeat(iterations) {
            val start = System.nanoTime()
            block()
            times.add((System.nanoTime() - start) / 1_000)
        }

        val avg = times.average()
        val min = times.minOrNull() ?: 0
        val max = times.maxOrNull() ?: 0

        Timber.d(tag, "Benchmark '$name': avg=${avg.toLong()}us, min=${min}us, max=${max}us, iterations=$iterations")
    }
}

inline fun <T> T.logD(tag: String = LogUtil.DEFAULT_TAG): T {
    LogUtil.d(toString(), tag)
    return this
}

inline fun <T> T.logI(tag: String = LogUtil.DEFAULT_TAG): T {
    LogUtil.i(toString(), tag)
    return this
}

inline fun <T> T.logW(tag: String = LogUtil.DEFAULT_TAG): T {
    LogUtil.w(toString(), tag)
    return this
}

inline fun <T> T.logE(tag: String = LogUtil.DEFAULT_TAG): T {
    LogUtil.e(toString(), tag)
    return this
}
