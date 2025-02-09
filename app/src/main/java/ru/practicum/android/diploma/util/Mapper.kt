package ru.practicum.android.diploma.util

fun interface Mapper<in From, out To> {
    fun map(from: From): To
}
