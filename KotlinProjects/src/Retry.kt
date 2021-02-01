import kotlin.random.Random

fun main() {
    var testUser1 = 10
    val signedInUser = 10
    var numberOfAttempts = 3
    while (numberOfAttempts > 0 && testUser1 == signedInUser) {
        testUser1 = 10
        numberOfAttempts = numberOfAttempts.minus(1)
        println("numberOfAttempts $numberOfAttempts $testUser1")
    }
    setUpRandomUsers()
}

private fun setUpRandomUsers() {
    val generator = getGenerator()
    val testUser1 = generator.generate()
    val testUser2 = generator.generate()
    val testUser3 = generator.generate()
    val testUser4 = generator.generate()
    val testUser5 = generator.generate()
    val testUser6 = generator.generate()
    val testUser7 = generator.generate()
//    val testUser8 = generator.generate()
//    val testUser9 = generator.generate()
    println(testUser1)
    println(testUser2)
    println(testUser3)
    println(testUser4)
    println(testUser5)
    println(testUser6)
    println(testUser7)
//    println(testUser8)
//    println(testUser9)
}

val userIds = mapOf(
    3 to "U08SNC6P4",
    4 to "U08SN0EDS",
    5 to "U08SNDJQ0",
    6 to "U08SNJ4UV",
    7 to "U08SN6RRU",
    8 to "U08SN3H9R",
    9 to "U08SNM1T3",
    10 to "U08SNCE3V",
    11 to "U08SN3CJW"
)

fun getGenerator(): TestUser.Generator = TestUser.DefaultGeneratorImpl(
    userIds,
    "teamDomain",
    "password",
    "teamId",
    null,
    "jason+",
    "test",
    9
)

fun getTestUserGenerator(): TestUser.Generator = getGenerator()
