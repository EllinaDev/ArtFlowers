package com.example.artflowersapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.artflowersapp.R
import com.example.artflowersapp.data.ArtModel
import com.example.artflowersapp.databinding.ItemFlowerBinding

class HomeAdapter(private val listener: FlowerListener, private val basketListener: FlowerBasketListener) :
    RecyclerView.Adapter<HomeAdapter.ArtViewHolder>() {

    private var flowers: List<ArtModel> = ArrayList()

    fun submitItems(flowers: List<ArtModel>) {
        this.flowers = flowers
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtViewHolder {
        return ArtViewHolder.from(parent, listener, basketListener)
    }

    override fun onBindViewHolder(holder: ArtViewHolder, position: Int) {
        holder.bind(flowers[position])
    }

    override fun getItemCount(): Int = flowers.size


    class ArtViewHolder(
        private val binding: ItemFlowerBinding,
        private val listener: FlowerListener,
        private val basketListener: FlowerBasketListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(flowers: ArtModel) {
            binding.tvName.text = flowers.name
            binding.tvPrice.text = flowers.price.toString()

            Glide.with(binding.root.context)
                .load(flowers.photoUri)
                .into(binding.ivPhoto)

            binding.ivPhoto.setOnClickListener{
                listener.onFlowerClick(flowers)
            }
            binding.btnAddToBasket.setOnClickListener {
                basketListener.onBasketClick(flowers)
            }
        }

        companion object {
            fun from(parent: ViewGroup, listener: FlowerListener, basketListener: FlowerBasketListener): ArtViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemFlowerBinding.inflate(inflater)
                return ArtViewHolder(binding, listener, basketListener)
            }
        }
    }

    interface FlowerListener {
        fun onFlowerClick(flowers: ArtModel)
    }

    interface FlowerBasketListener {
        fun onBasketClick(flowers: ArtModel)
    }


}