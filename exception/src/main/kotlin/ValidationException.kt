class ValidationException(val errorCode: Array<ErrorCode>) :
    RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_NAME(100, "Ошибка валидации имени/фамилии"),
    INVALID_PHONE(200, "Ошибка валидации номера телефона"),
    INVALID_EMAIL(300, "Ошибка валидации email"),
    INVALID_SNILS(400, "Ошибка валидации СНИЛС")
}