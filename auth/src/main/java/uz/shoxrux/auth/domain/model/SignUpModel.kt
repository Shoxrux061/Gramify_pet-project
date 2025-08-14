package uz.shoxrux.auth.domain.model

data class SignUpModel(

    val id: String = "",
    val username: String = "",
    val email: String = "",
    val password:String ="",
    val bio: String = "",
    val avatarUrl: String = "",
    val profileImageUrl: String = "",
    val followingCount: Int = 0,
    val followersCount: Int = 0

)
