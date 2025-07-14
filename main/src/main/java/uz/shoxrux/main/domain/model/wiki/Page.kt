package uz.shoxrux.main.domain.model.wiki

data class Page(
    val ns: Int?,
    val original: Original? = null,
    val pageid: Int,
    val title: String,
    val extract: String?
)