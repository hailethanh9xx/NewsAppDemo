package com.aiden.newsdemo.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import coil.load
import com.aiden.newsdemo.R
import com.aiden.newsdemo.databinding.FragmentDetailBinding
import com.aiden.newsdemo.utils.viewBinding
import data.entities.Article

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val binding by viewBinding { FragmentDetailBinding.bind(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val content = arguments?.getParcelable<Article>(NEWS_KEY)
        content?.let {
            showArticleDetail(it)
        }
    }

    private fun showArticleDetail(article: Article) {
        binding.tvTitle.text = article.title
        binding.tvDescription.text = article.description
        binding.imvPhoto.load(article.urlToImage) {
            placeholder(R.drawable.ic_place_holder)
            error(R.drawable.ic_place_holder)
        }
    }

    companion object {
        const val NEWS_KEY = "NEWS_KEY"
    }
}