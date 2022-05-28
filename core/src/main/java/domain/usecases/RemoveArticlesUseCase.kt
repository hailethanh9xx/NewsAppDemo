package domain.usecases

import javax.inject.Inject

class RemoveArticlesUseCase @Inject constructor () : BaseUseCase() {
    suspend operator fun invoke() = articleRepository.clearArticles()
}