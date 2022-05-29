package com.assignment.demo.starwarsapp.utils

abstract class EndlessScrollListener(
    layoutManager: androidx.recyclerview.widget.LinearLayoutManager,
    private val visibleThreshold: Int = 5
) : androidx.recyclerview.widget.RecyclerView.OnScrollListener() {
    private var currentPage = 0
    private var previousTotalItemCount = 0
    private var loading = true
    private val startingPageIndex = 0

    private var layoutManager: androidx.recyclerview.widget.RecyclerView.LayoutManager =
        layoutManager


    private fun getLastVisibleItem(lastVisibleItemPositions: IntArray): Int {
        var maxSize = 0
        for (i in lastVisibleItemPositions.indices) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i]
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i]
            }
        }
        return maxSize
    }

    override fun onScrolled(
        view: androidx.recyclerview.widget.RecyclerView,
        dx: Int,
        dy: Int
    ) {

        val totalItemCount = layoutManager.itemCount

        val lastVisibleItemPosition = when (layoutManager) {
            is androidx.recyclerview.widget.StaggeredGridLayoutManager -> {
                getLastVisibleItem(
                    (layoutManager as androidx.recyclerview.widget.StaggeredGridLayoutManager).findLastVisibleItemPositions(
                        null
                    )
                )
            }
            is androidx.recyclerview.widget.GridLayoutManager ->
                (layoutManager as androidx.recyclerview.widget.GridLayoutManager).findLastVisibleItemPosition()
            is androidx.recyclerview.widget.LinearLayoutManager ->
                (layoutManager as androidx.recyclerview.widget.LinearLayoutManager).findLastVisibleItemPosition()
            else -> 0
        }

        if (totalItemCount < previousTotalItemCount) {
            this.currentPage = this.startingPageIndex
            this.previousTotalItemCount = totalItemCount
            if (totalItemCount == 0) {
                this.loading = true
            }
        }

        if (loading && totalItemCount > previousTotalItemCount) {
            loading = false
            previousTotalItemCount = totalItemCount
        }

        if (!loading && lastVisibleItemPosition + visibleThreshold > totalItemCount) {
            currentPage++
            onLoadMore(currentPage, totalItemCount, view)
            loading = true
        }
    }

    fun resetState() {
        this.currentPage = this.startingPageIndex
        this.previousTotalItemCount = 0
        this.loading = true
    }

    protected abstract fun onLoadMore(
        page: Int,
        totalItemsCount: Int,
        view: androidx.recyclerview.widget.RecyclerView?
    )

}