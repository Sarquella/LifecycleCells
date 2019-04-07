package dev.sarquella.lifecyclecells

import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil

/*
 * Created by Adrià Sarquella on 16/08/2018.
 * adria@sarquella.dev
 */

abstract class LifecyclePagedListAdapter<T, VH : LifecycleViewHolder>(
        diffCallback: DiffUtil.ItemCallback<T>) :
        PagedListAdapter<T, VH>(diffCallback) {

    override fun onViewAttachedToWindow(holder: VH) {
        super.onViewAttachedToWindow(holder)
        holder.onAttached()
    }

    override fun onViewDetachedFromWindow(holder: VH) {
        super.onViewDetachedFromWindow(holder)
        holder.onDetached()
    }

    override fun onViewRecycled(holder: VH) {
        super.onViewRecycled(holder)
        holder.onRecycled()
    }

}