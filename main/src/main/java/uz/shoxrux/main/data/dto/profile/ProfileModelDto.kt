package uz.shoxrux.main.data.dto.profile

data class ProfileModelDto(
    val id: String = "",
    val username: String = "",
    val bio: String = "",
    val profileImageUrl: String = "",
    val followingCount: Int = 0,
    val followersCount: Int = 0
)