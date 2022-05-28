package com.aiden.newsdemo.presentation.articles

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.aiden.newsdemo.R
import com.aiden.newsdemo.databinding.FragmentArticlesBinding
import com.aiden.newsdemo.presentation.articles.adapter.ArticlesAdapter
import com.aiden.newsdemo.presentation.detail.DetailFragment
import com.aiden.newsdemo.utils.*
import dagger.hilt.android.AndroidEntryPoint
import data.entities.Article
import data.entities.State
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by Aiden ( hai Le Thanh ) on 27/05/2022.
 * aiden9xx@gmail.com
 */
/**
 * [ArticlesFragment] will list [Article] items.
 */
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ArticlesFragment : Fragment(R.layout.fragment_articles) {

    private val binding by viewBinding { FragmentArticlesBinding.bind(it) }
    private val viewModel by viewModels<ArticlesViewModel>()
    private val adapter = ArticlesAdapter(this::onItemClicked)

    override fun onStart() {
        super.onStart()
        observeArticles()
        observeNetwork()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    /**
     * Observe data
     * ( we also can create a BaseFragment and the put abstract
     * class observeArticles in BaseFragment )
     */
    private fun observeArticles() {
        viewModel.articlesLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is State.Loading<*> -> {
                    showLoading(true)
                }
                is State.Success -> {
                    if (state.data.isNotEmpty()) {
                        adapter.submitList(state.data.toMutableList().filter { it.urlToImage != null })
                        showLoading(false)
                    }
                }
                is State.Error -> {
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                    showLoading(false)
                }
            }
        }
    }

    private fun getArticles() = viewModel.getArticles()

    /**
     * Initialize recyclerview
     */
    private fun initView() {
        binding.run {
            rvRecyclerview.adapter = adapter
            swRefresh.setOnRefreshListener { getArticles() }
        }

        viewModel.articlesLiveData.value?.let { currentState ->
            if (!currentState.isSuccessful()) {
                getArticles()
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.swRefresh.isRefreshing = isLoading
    }

    /**
     * Navigate to the detail fragment.
     */
    private fun onItemClicked(article: Article) {
        findNavController().navigate(
            R.id.action_articlesFragment_to_detailFragment,
            bundleOf(
                DetailFragment.NEWS_KEY to article
            )
        )
    }

    /**
     * When network change ( We can bring it to BaseFragment )
     */
    private fun observeNetwork() {
        NetworkUtils.getNetworkLiveData(requireContext()).observe(this) { isConnected ->
            if (!isConnected) {
                binding.tvNetworkStatus.text =
                    getString(R.string.no_internet)
                binding.lnNetwork.apply {
                    show()
                    setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.fail
                        )
                    )
                }
            } else {
                if (viewModel.articlesLiveData.value is State.Error || adapter.itemCount == 0) {
                    getArticles()
                }
                binding.tvNetworkStatus.text =
                    getString(R.string.internet_connected)
                binding.lnNetwork.apply {
                    setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.success
                        )
                    )
                    animationHide()
                }
            }
        }
    }
}