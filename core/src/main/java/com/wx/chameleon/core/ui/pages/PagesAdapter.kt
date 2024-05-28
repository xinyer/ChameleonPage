package com.wx.chameleon.core.ui.pages

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.wx.chameleon.core.model.Page
import com.wx.chameleon.databinding.ItemPagesBinding

class PagesAdapter(private val onClick: (Page) -> Unit) : RecyclerView.Adapter<PagesAdapter.PagesViewHolder>() {

    private val items: MutableList<Page> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagesViewHolder {
        val binding = ItemPagesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PagesViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: PagesViewHolder, position: Int) {
        holder.bind(items[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(items: List<Page>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    inner class PagesViewHolder(private val binding: ItemPagesBinding) : ViewHolder(binding.root) {
        fun bind(item: Page) {
            binding.title.text = item.title
            binding.root.setOnClickListener { onClick.invoke(item) }
        }
    }
}
