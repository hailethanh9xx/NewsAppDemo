package domain.usecases

import domain.repositories.ArticleDataSourceRepository
import javax.inject.Inject

open class BaseUseCase {

    @Inject lateinit var articleRepository: ArticleDataSourceRepository
}