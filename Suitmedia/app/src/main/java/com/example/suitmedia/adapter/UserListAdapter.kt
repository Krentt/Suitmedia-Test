package com.example.suitmedia.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.suitmedia.R
import com.example.suitmedia.databinding.ItemUserBinding
import com.example.suitmedia.network.DataItem

class UserListAdapter: PagingDataAdapter<DataItem, UserListAdapter.MyViewHolder>(DIFF_CALLBACK) {

    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback{
        fun onItemCLicked(data: DataItem)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
            holder.itemView.setOnClickListener { onItemClickCallback.onItemCLicked(data) }
        }
    }

    class MyViewHolder(private val binding: ItemUserBinding, private val context: Context ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataItem) {
            binding.tvNama.text = context.getString(R.string.nama_user, data.firstName, data.lastName)
            binding.tvEmail.text = data.email
            Glide.with(context)
                .load(data.avatar)
                .into(binding.ivProfile)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}