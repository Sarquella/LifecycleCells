package dev.sarquella.lifecyclecells

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import android.view.View
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.RecyclerView

/*
 * Created by Adrià Sarquella on 16/08/2018.
 * adria@sarquella.dev
 */

abstract class LifecycleViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView), LifecycleOwner {

    private var lifecycleRegistry: LifecycleRegistry

    init {
        lifecycleRegistry = createLifeCycle()
    }

    private fun createLifeCycle(): LifecycleRegistry {
        val lifecycle = LifecycleRegistry(this)
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        return lifecycle
    }

    @CallSuper
    open fun onAttached() {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
    }

    @CallSuper
    open fun onDetached() {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    }

    @CallSuper
    open fun onRecycled() {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        lifecycleRegistry = createLifeCycle()
    }

    override fun getLifecycle(): Lifecycle = lifecycleRegistry
}