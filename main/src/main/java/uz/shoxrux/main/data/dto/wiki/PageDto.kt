package uz.shoxrux.main.data.dto.wiki

data class PageDto(
    val ns: Int?,
    val original: OriginalDto?,
    val pageid: Int,
    val title: String,
    val extract:String?
)