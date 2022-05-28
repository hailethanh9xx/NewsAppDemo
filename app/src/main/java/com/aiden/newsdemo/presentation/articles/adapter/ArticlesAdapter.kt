package com.aiden.newsdemo.presentation.articles.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.aiden.newsdemo.databinding.ItemArticleBinding
import com.aiden.newsdemo.presentation.articles.viewholder.ArticlesViewHolder
import data.entities.Article

/**
 * Created by Aiden ( hai Le Thanh ) on 27/05/2022.
 * aiden9xx@gmail.com
 */
class ArticlesAdapter(
    private val onItemClicked: (Article) -> Unit
) : ListAdapter<Article, ArticlesViewHolder>(articleItemCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ArticlesViewHolder(
        ItemArticleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) =
        holder.bind(getItem(position), onItemClicked)

    companion object {
        private val articleItemCallBack = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean =
                oldItem.articleId == newItem.articleId

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean =
                oldItem == newItem
        }
    }
}
