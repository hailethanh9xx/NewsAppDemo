package com.aiden.newsdemo.presentation.articles.viewholder

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.aiden.newsdemo.R
import com.aiden.newsdemo.databinding.ItemArticleBinding
import com.aiden.newsdemo.utils.dateTimeFormat
import data.entities.Article

/**
 * Created by Aiden ( hai Le Thanh ) on 27/05/2022.
 * aiden9xx@gmail.com
 */
class ArticlesViewHolder(private val binding: ItemArticleBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(article: Article, onItemClicked: (Article) -> Unit) {
        binding.imvAvatar.load(article.urlToImage.toString()) {
            placeholder(R.drawable.ic_place_holder)
            error(R.drawable.ic_place_holder)
        }
        binding.tvArticleTitle.text = article.title
        binding.tvArticleDescription.text = article.description
        binding.tvDateTime.text = article.publishedAt?.dateTimeFormat()

        binding.root.setOnClickListener {
            onItemClicked(article)
        }
    }
}