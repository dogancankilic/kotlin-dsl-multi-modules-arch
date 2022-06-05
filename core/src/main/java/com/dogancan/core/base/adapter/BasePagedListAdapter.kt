package com.dogancan.core.base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BasePagedListAdapter<T : Any>(
    itemsSame: (T, T) -> Boolean,
    contentsSame: (T, T) -> Boolean
) : PagingDataAdapter<T, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(old: T, new: T): Boolean = itemsSame(old, new)
    override fun areContentsTheSame(old: T, new: T): Boolean = contentsSame(old, new)
}) {


    internal var recyclerView: RecyclerView? = null

    /**
     * Called when RecyclerView needs a new [RecyclerView.ViewHolder] of the given type to
     * represent an item.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     * an adapter position.
     * @param inflater Instantiates a layout XML file into its corresponding View objects.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see PagedListAdapter.onCreateViewHolder
     */
    abstract fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder

    /**
     * Called when RecyclerView needs a new [RecyclerView.ViewHolder] of the given type to
     * represent an item.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     * an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see ListAdapter.onCreateViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        onCreateViewHolder(
            parent = parent,
            inflater = LayoutInflater.from(parent.context),
            viewType = viewType
        )

    /**
     * Called by RecyclerView when it starts observing this Adapter.
     *
     * @param recyclerView The RecyclerView instance which started observing this adapter.
     * @see PagedListAdapter.onAttachedToRecyclerView
     */
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        this.recyclerView = recyclerView
        super.onAttachedToRecyclerView(recyclerView)
    }

    /**
     * Called by RecyclerView when it stops observing this Adapter.
     *
     * @param recyclerView The RecyclerView instance which stopped observing this adapter.
     * @see PagedListAdapter.onDetachedFromRecyclerView
     */
    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        this.recyclerView = null
        super.onDetachedFromRecyclerView(recyclerView)
    }

}