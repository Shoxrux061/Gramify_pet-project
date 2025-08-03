package uz.shoxrux.main.data.dto.like

data class LikeModel(
    val likedPost:String = "",
    val owner:String = "",
    val type:LikeType = LikeType.Post
)
