package com.qomunal.opensource.androidresearch.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.qomunal.opensource.androidresearch.databinding.ItemMainBinding
import com.qomunal.opensource.androidresearch.model.MainModel

/**
 * Created by faisalamircs on 27/02/2025
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 */


class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private val asyncListDiffer =
        AsyncListDiffer(this, object : DiffUtil.ItemCallback<MainModel>() {
            override fun areItemsTheSame(oldItem: MainModel, newItem: MainModel): Boolean {
                return oldItem.img == newItem.img
            }

            override fun areContentsTheSame(oldItem: MainModel, newItem: MainModel): Boolean {
                return oldItem == newItem
            }
        })


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            binding = ItemMainBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return asyncListDiffer.currentList.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(asyncListDiffer.currentList[position])
    }

    fun setItem(item: List<MainModel>) {
        if (item.isEmpty()) {
            asyncListDiffer.submitList(listOf())
        } else {
            asyncListDiffer.submitList(item.map { it })
        }
    }

    fun clearItems() {
        asyncListDiffer.submitList(null)
    }

    inner class MainViewHolder(private val binding: ItemMainBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: MainModel) {
            binding.apply {
                iv.setImageResource(data.img ?: 0)
            }
        }

    }


}