package com.lyldding.modulekotlin

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @author lyldding
 * @date 2019/10/23
 */

fun main(args: Array<String>) {

}

suspend fun test() {
    println("hahaha")
}

fun start() {
    println(addSum(1, 3))
}

fun addSum(a: Int, b: Int) = a + b

fun maxOf(a: Int, b: Int) = if (a > b) a else b

fun parseInt(str: String): Int? {
    return str.toIntOrNull()
}