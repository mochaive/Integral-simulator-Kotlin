package com.starbox.integral

import java.util.*
import kotlin.collections.HashMap
import kotlin.math.roundToLong


fun main(args: Array<String>) {
    while(true) {
        val scanner: Scanner = Scanner(System.`in`)
        var number: Double = 0.0
        var area: Double = 0.0
        var check: Int = 0

        println("구분구적법을 기반으로 하여 적분하는 적분 프로그램 입니다.")
        print("함수의 차수를 입력해 주세요.: ")
        val order: Double = readLine()!!.toDouble()
        println("")

        val equation: HashMap<Int, Double> = hashMapOf()

        for (i in order.toInt() downTo 0) {
            val orderMessage: String = when (i) {
                0 -> "상수항"
                else -> "${i}차항"
            }

            print("${orderMessage}의 계수를 입력해 주세요.: ")

            val coefficient: Double = readLine()!!.toDouble()
            equation[i] = coefficient
        }

        println("")
        println("적분 구간을 입력해 주세요.")
        println("ex) 0부터 3까지: 0 3")
        val start: Double = scanner.next().toDouble()
        val end: Double = scanner.next().toDouble()

        println("")
        print("적분 구간을 쪼개고 싶은 갯수를 입력하세요. 많이 쪼갤수록 정확도랑 비례합니다.: ")
        val accuracy: Double = readLine()!!.toDouble()

        // 넓이 구하기
        while (true) {
            val base: Double = (end - start) / accuracy
            val x: Double = start + base * number

            area += base * calculateHeight(x, order.toInt(), equation)

            if (x == end) break

            number++
        }

        val equationText: StringBuffer = StringBuffer()
        equationText.append("함수:")

        for(i in order.toInt() downTo 0) {
            if(i == 0) {
                equationText.append(" ${equation[0]!!.toInt()}")
            } else {
                equationText.append(" ${equation[i]!!.toInt()}x^${i} +")
            }
        }

        println(equationText)

        println((area * 100).roundToLong() / 100.0)
        println("")

        print("다시 계산하고 싶으면 1번, 그냥 종료할 시 1번 제외 아무 키나 눌러주세요.: ")
        check = readLine()!!.toInt()
        println("")
        if(check == 1) continue
        else break
    }
}

fun calculateHeight(x: Double, order: Int, equation: HashMap<Int, Double>): Double {
    var height: Double = 0.0

    for(i in 0..order) {
        height += (equation[i]!! * calculateValue(x, order))
    }

    return height
}

fun calculateValue(x: Double, order: Int): Double {
    var value: Double = 1.0
    var i: Int = order

    while(i >= 1) {
        value *= x
        i--
    }

    return value
}

