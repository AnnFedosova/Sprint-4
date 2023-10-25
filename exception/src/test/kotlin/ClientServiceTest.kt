import com.google.gson.Gson
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

class ClientServiceTest {

    private val gson = Gson()
    private val clientService = ClientService()

    @Test
    fun `success save client`() {
        val client = getClientFromJson("/success/user.json")
        val result = clientService.saveClient(client)
        assertNotNull(result)
    }

    @Test
    fun `fail save client - phone validation error`() {
        val client = getClientFromJson("/fail/user_with_bad_phone.json")
        val exception = assertFailsWith<ValidationException>("Should throw an Exception") {
            clientService.saveClient(client)
        }
        assertEquals(1, exception.errorCode.size)
        assertEquals(ErrorCode.INVALID_PHONE, exception.errorCode[0])
    }

    @Test
    fun `fail save client - first name validation error`() {
        val client = getClientFromJson("/fail/user_with_bad_first_name.json")
        val exception = assertFailsWith<ValidationException>("Should throw an Exception") {
            clientService.saveClient(client)
        }
        assertEquals(1, exception.errorCode.size)
        assertEquals(ErrorCode.INVALID_NAME, exception.errorCode[0])
    }

    @Test
    fun `fail save client - last name validation error`() {
        val client = getClientFromJson("/fail/user_with_bad_last_name.json")
        val exception = assertFailsWith<ValidationException>("Should throw an Exception") {
            clientService.saveClient(client)
        }
        assertEquals(1, exception.errorCode.size)
        assertEquals(ErrorCode.INVALID_NAME, exception.errorCode[0])
    }

    @Test
    fun `fail save client - email validation error`() {
        val client = getClientFromJson("/fail/user_with_bad_email.json")
        val exception = assertFailsWith<ValidationException>("Should throw an Exception") {
            clientService.saveClient(client)
        }
        assertEquals(1, exception.errorCode.size)
        assertEquals(ErrorCode.INVALID_EMAIL, exception.errorCode[0])
    }

    @Test
    fun `fail save client - snils validation error`() {
        val client = getClientFromJson("/fail/user_with_bad_snils.json")
        val exception = assertFailsWith<ValidationException>("Should throw an Exception") {
            clientService.saveClient(client)
        }
        assertEquals(1, exception.errorCode.size)
        assertEquals(ErrorCode.INVALID_SNILS, exception.errorCode[0])
    }

    @Test
    fun `fail save client - validation errors`() {
        val client = getClientFromJson("/fail/user_data_corrupted.json")
        val exception = assertFailsWith<ValidationException>("Should throw an Exception") {
            clientService.saveClient(client)
        }
        assertEquals(5, exception.errorCode.size)
    }

    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

}