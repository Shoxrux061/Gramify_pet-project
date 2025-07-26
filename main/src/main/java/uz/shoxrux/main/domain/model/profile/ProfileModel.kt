package uz.shoxrux.main.domain.model.profile

import uz.shoxrux.main.domain.model.post.PostModel

data class ProfileModel(
    val id: String = "",
    val fullName: String = "",
    val bio: String = "",
    val avatarUrl: String = "",
    val profileImageUrl: String = "",
    val posts: List<PostModel> = emptyList(),
    val followingCount: Int = 0,
    val followersCount: Int = 0
)