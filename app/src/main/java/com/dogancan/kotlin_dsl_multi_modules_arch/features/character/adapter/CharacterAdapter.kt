package com.dogancan.kotlin_dsl_multi_modules_arch.features.character.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dogancan.core.base.adapter.BasePagedListAdapter
import com.dogancan.core.base.adapter.BaseViewHolder
import com.dogancan.domain.character.CharacterUiModel
import com.dogancan.kotlin_dsl_multi_modules_arch.databinding.CharacterListItemBinding
import javax.inject.Inject

/**
 * @author dogancankilic
 * Created at 4.06.2022
 */
class CharacterAdapter @Inject constructor() : BasePagedListAdapter<CharacterUiModel>(
    itemsSame = { old, new -> old.id == new.id },
    contentsSame = { old, new -> old == new }
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder = CharacterViewHolder(parent, inflater, onItemClick)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CharacterViewHolder -> getItem(position)?.let { holder.bind(it) }
        }
    }

    var onItemClick: ((item: CharacterUiModel) -> Unit)? = null

    inner class CharacterViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        onItemClick: ((item: CharacterUiModel) -> Unit)?
    ) : BaseViewHolder<CharacterListItemBinding>(
        binding = CharacterListItemBinding.inflate(inflater, parent, false)
    ) {
        fun bind(item: CharacterUiModel) {
            binding.tvName.text = item.name

            binding.root.setOnClickListener {
                onItemClick?.invoke(item)
            }
        }
    }
}
