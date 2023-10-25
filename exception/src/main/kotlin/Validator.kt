abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

class PhoneValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val errorList: MutableList<ErrorCode> = mutableListOf()
        if (value == null || !"""^([7,8])\d{10}${'$'}""".toRegex().matches(value)) {
            errorList.add(ErrorCode.INVALID_PHONE)
        }
        return errorList
    }
}

class NameValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val errorList: MutableList<ErrorCode> = mutableListOf()
        if (value == null || !"""^[а-яА-ЯёЁ]{1,16}${'$'}""".toRegex().matches(value)) {
            errorList.add(ErrorCode.INVALID_NAME)
        }
        return errorList
    }
}

class EmailValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val errorList: MutableList<ErrorCode> = mutableListOf()
        if (value == null || value.length > 32 || !"""^[\w]+@[\w]+\.[\w]{2,4}${'$'}""".toRegex().matches(value)) {
            errorList.add(ErrorCode.INVALID_EMAIL)
        }
        return errorList
    }
}

class SnilsValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val errorList: MutableList<ErrorCode> = mutableListOf()
        if (value == null || !"""^\d{11}${'$'}""".toRegex().matches(value) || !isValidSum(value)) {
            errorList.add(ErrorCode.INVALID_SNILS)
        }
        return errorList
    }

    private fun isValidSum(snils: String): Boolean {
        val lastNums = snils.substring(9, 11).toInt()
        var sum = 0
        (0..8).forEach { i ->
            sum += (9 - i) * snils[i].digitToInt()
        }
        if (sum < 100 && sum != lastNums
            ||
            sum == 100 && lastNums != 0
        ) {
            return false
        }
        if (sum > 100) {
            val remainder = sum % 101
            if (remainder == 100) {
                if (lastNums != 0) {
                    return false
                }
            } else {
                if (lastNums != remainder) {
                    return false
                }
            }
        }
        return true
    }

}