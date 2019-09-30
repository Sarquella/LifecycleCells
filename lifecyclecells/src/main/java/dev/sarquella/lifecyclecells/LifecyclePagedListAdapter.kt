package dev.sarquella.lifecyclecells

import androidx.annotation.CallSuper
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil

/*
 * Created by Adrià Sarquella on 16/08/2018.
 * adria@sarquella.dev
 */

abstract class LifecyclePagedListAdapter<T, VH : LifecycleViewHolder>(
        diffCallback: DiffUtil.ItemCallback<T>) :
        PagedListAdapter<T, VH>(diffCallback) {

    @CallSuper
    override fun onViewAttachedToWindow(holder: VH) {
        super.onViewAttachedToWindow(holder)
        holder.onAttached()
    }

    @CallSuper
    override fun onViewDetachedFromWindow(holder: VH) {
        super.onViewDetachedFromWindow(holder)
        holder.onDetached()
    }

    @CallSuper
    override fun onViewRecycled(holder: VH) {
        super.onViewRecycled(holder)
        holder.onRecycled()
    }

}