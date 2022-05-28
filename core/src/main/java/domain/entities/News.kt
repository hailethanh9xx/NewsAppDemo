package domain.entities

import data.entities.Article

data class News(
    var status: String?,
    var totalResults: Int?,
    var articles: List<Article>
)