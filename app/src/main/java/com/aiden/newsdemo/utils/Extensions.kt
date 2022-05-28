@file:Suppress("unused")

package com.aiden.newsdemo.utils
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Aiden ( hai Le Thanh ) on 27/05/2022.
 * aiden9xx@gmail.com
 */
fun String.dateTimeFormat(): String {
    return if (!this.isNullOrEmpty()) {
        val sourceSdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        val requiredSdf = SimpleDateFormat("MM.dd.yyyy HH:mm", Locale.getDefault())
        requiredSdf.format(sourceSdf.parse(this))
    } else {
        ""
    }
}
