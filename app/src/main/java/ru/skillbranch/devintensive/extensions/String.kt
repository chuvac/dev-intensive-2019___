package ru.skillbranch.devintensive.extensions

import java.util.regex.Pattern

fun String.truncate(value : Int = 16) : String{
    val str = this.trimEnd()
    val length = str.length
    return if (length > value)
        this.substring(0 until value).trimEnd() + "..."
    else str

}

fun String.stripHtml() : String{
    val pattern = Pattern.compile("(<.*?>)|(&.*?;)|([ ]{2,})")
    val matcher = pattern.matcher(this)
    return matcher.replaceAll(" ").trim()
}