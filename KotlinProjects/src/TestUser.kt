import kotlin.random.Random

data class TestUser constructor(
    @JvmField val teamDomain: String,
    @JvmField val username: String,
    @JvmField val email: String,
    @JvmField val password: String,
    @JvmField val userId: String,
    @JvmField val teamId: String,
    @JvmField val enterpriseId: String? = null,
    @JvmField val isEnterpriseUser: Boolean = false,
    @JvmField val twoFactorAuthSecret: String? = null
) {
    interface Generator {
        val idMap: Map<Int, String>
        val teamDomain: String
        val password: String
        val teamId: String
        val enterpriseId: String?
        val baseEmail: String
        val baseUserNamePrefix: String
        val maxUsers: Int
        val isEnterprise: Boolean
        val twoFactorAuthSecretMap: Map<Int, String>?

        /**
         * Get a random user from the IDs provided by [idSet].
         *
         * @throws IllegalStateException if we're really unlucky and a random user avoiding param above cannot be generated
         */
        fun generate(): TestUser

        fun random(vararg avoidUsersWithIds: String): TestUser {
            // attempt (up to maxUsers times) to generate a random user that does not collide with the provided list of users.
            // we'd have to be really unlucky, but it is theoretically possible that we won't get it, therefore
            // we handle that case by throwing a RE.
            for (x in 0 until maxUsers) {
                val i = Random.nextInt(from = 3, until = idMap.size)
                val userId = idMap[i] ?: throw NoSuchElementException("No user id for index $i")
                val twoFactorAuthSecret = twoFactorAuthSecretMap?.get(i)
                if (userId !in avoidUsersWithIds) {
                    return TestUser(
                        teamDomain,
                        "$baseUserNamePrefix$i",
                        "$baseEmail$i@slack-corp.com",
                        password,
                        userId,
                        teamId,
                        enterpriseId,
                        isEnterpriseUser = isEnterprise,
                        twoFactorAuthSecret = twoFactorAuthSecret
                    )
                }
            }
            throw IllegalStateException("Could not generate random user different from $avoidUsersWithIds")
        }
    }

    @Suppress("LongParameterList")
    class DefaultGeneratorImpl(
        override val idMap: Map<Int, String>,
        override val teamDomain: String,
        override val password: String,
        override val teamId: String,
        override val enterpriseId: String? = null,
        override val baseEmail: String,
        override val baseUserNamePrefix: String,
        override val maxUsers: Int = 30,
        override val isEnterprise: Boolean = false,
        override val twoFactorAuthSecretMap: Map<Int, String>? = null
    ) : Generator {

        val excluded = mutableSetOf<String>()

        override fun generate(): TestUser {
            val user = random(*excluded.toTypedArray())
            excluded += user.userId
            return user
        }
    }
}