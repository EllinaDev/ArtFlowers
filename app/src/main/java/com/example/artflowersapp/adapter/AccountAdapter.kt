package com.example.artflowersapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.artflowersapp.R
import com.example.artflowersapp.data.ArtModel
import com.example.artflowersapp.databinding.ItemFlowerAccountBinding

class AccountAdapter(private val deleteListener: AccountDeleteListener, private val flowerListener: AccountFlowerListener) : RecyclerView.Adapter<AccountAdapter.AccountVH>() {

    private var flowers: List<ArtModel> = ArrayList()

    fun submitItems(flowers: List<ArtModel>) {
        this.flowers = flowers
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountVH {
        return AccountVH.from(parent,deleteListener,flowerListener)
    }

    override fun onBindViewHolder(holder: AccountVH, position: Int) {
        holder.bind(flowers[position])
    }

    override fun getItemCount(): Int = flowers.size


    class AccountVH(private val binding: ItemFlowerAccountBinding, private val deleteListener: AccountDeleteListener, private val flowerListener: AccountFlowerListener) : RecyclerView.ViewHolder(binding.root){
        fun bind(flowers: ArtModel) {
            binding.tvName.text = flowers.name
            binding.tvPrice.text = flowers.price.toString()

            Glide.with(binding.root.context)
                .load(flowers.photoUri)
                .into(binding.ivPhoto)

            binding.ivDelete.setOnClickListener {
                deleteListener.delete(flowers)
            }
            binding.ivPhoto.setOnClickListener {
                flowerListener.onFlowerClick(flowers)
            }
        }

        companion object {
            fun from(parent: ViewGroup, deleteListener: AccountDeleteListener, flowerListener: AccountFlowerListener): AccountVH {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemFlowerAccountBinding.inflate(inflater)
                return AccountAdapter.AccountVH(binding, deleteListener, flowerListener)
            }
        }

    }

    interface AccountDeleteListener {
        fun delete(artModel: ArtModel)
    }

    interface AccountFlowerListener {
        fun onFlowerClick(artModel: ArtModel)
    }


}