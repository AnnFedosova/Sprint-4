package ru.sber.generics

// 1.
fun <F : Comparable<F>, S : Comparable<S>> compare(p1: Pair<F, S>, p2: Pair<F, S>): Boolean {
    return p1.first == p2.first && p1.second == p2.second
}

// 2.
fun <T : Comparable<T>> countGreaterThan(anArray: Array<T>, elem: T): Int {
    return anArray.count { it > elem }
}

// 3.
class Sorter<T : Comparable<T>> {
    val list: MutableList<T> = mutableListOf()

    fun add(value: T) {
        list.add(value)
        list.sort()
    }
}

// 4.
class Stack<T> {
    private val list: MutableList<T> = mutableListOf()

    fun push(elem: T): Boolean = list.add(elem)

    fun pop(): T = list.removeLast()

    fun isEmpty(): Boolean = list.isEmpty()

}