package domain.usecases

import javax.inject.Inject

class GetArticlesUseCase @Inject constructor () : BaseUseCase() {
    suspend operator fun invoke() = articleRepository.getArticles()
}