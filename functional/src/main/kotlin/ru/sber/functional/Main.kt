package ru.sber.functional

fun main() {
    //Проверка возведения в степень
    val factory = PowFactory

    val lambdaPow1 = factory.buildPowFunction(1)
    val lambdaPow2 = factory.buildPowFunction(2)
    val lambdaPow3 = factory.buildPowFunction(3)

    val a1 = lambdaPow1.invoke(10) //10
    val a2 = lambdaPow2.invoke(10) //100
    val a3 = lambdaPow3.invoke(2) //8

    println("Все работает")
}