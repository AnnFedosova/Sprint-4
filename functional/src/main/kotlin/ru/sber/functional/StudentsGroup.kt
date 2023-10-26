package ru.sber.functional

import kotlin.random.Random

class StudentsGroup {

    var students: List<Student> = mutableListOf(
        createSimpleStudent(17),
        createSimpleStudent(29),
        createSimpleStudent(24),
        createSimpleStudent(18),
        createSimpleStudent(30)
    )

    fun filterByPredicate(filterFunc: (student: Student) -> Boolean): List<Student> {
        return students.stream().filter(filterFunc).toList()
    }
}

fun main() {
    val group = StudentsGroup()
    val youngStudent: List<Student> = group.filterByPredicate { s -> s.age < 25 }
    print("youngStudent: $youngStudent")
}

private fun createSimpleStudent(age: Int): Student {
    val studentLuckyNumber = Random.nextDouble(0.0, 5.0)
    return Student(
        firstName = "Иван$studentLuckyNumber",
        lastName = "Иванов$studentLuckyNumber",
        middleName = "Иванович$studentLuckyNumber",
        age = age,
        averageRate = studentLuckyNumber,
        city = "Москва",
        specialization = "Специализация отсутствует",
        prevEducation = null
    )
}
