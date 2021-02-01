fun main() {
    val username1 = User("Hunvil", "1234")
    val username2 = User("Rodrigues", "6789")

    val chatName = "${username1}, ${username2}"

    val userIds = parseUserIds(chatName)

}
data class User(val username:String, val userId: String)
