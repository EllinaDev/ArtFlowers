package com.example.artflowersapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.artflowersapp.R
import com.example.artflowersapp.data.ArtModel
import com.example.artflowersapp.data.BasketModel
import com.example.artflowersapp.databinding.ItemBasketBinding

class BasketAdapter(private val listener: BasketListener, private val flowerListener: FlowerListener) : RecyclerView.Adapter<BasketAdapter.BasketViewHolder>() {

    private var flowers: List<BasketModel> = ArrayList()

    fun submitItems(flowers: List<BasketModel>) {
        this.flowers = flowers.toList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        return BasketViewHolder.from(parent, listener, flowerListener)
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        holder.bind(flowers[position])
    }

    override fun getItemCount(): Int = flowers.size

    class BasketViewHolder(private val binding: ItemBasketBinding, private val listener: BasketListener, private val flowerListener: FlowerListener) : RecyclerView.ViewHolder(binding.root){

        fun bind(flowers: BasketModel){
            binding.tvPrice.text = flowers.flower.price.toString()
            binding.tvName.text = flowers.flower.name
            binding.tvDescription.text = flowers.flower.description.toString()
            binding.ivPhoto.setImageResource(R.drawable.flower_two)
            binding.ivDelete.setOnClickListener {
                listener.deleteFromBasket(flowers)
            }
            binding.ivPhoto.setOnClickListener{
                flowerListener.onFlowerClick(flowers.flower)
            }
        }
        companion object{
            fun from(parent: ViewGroup, listener: BasketListener, flowerListener: FlowerListener): BasketViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemBasketBinding.inflate(inflater)
                return BasketViewHolder(binding, listener, flowerListener)
            }
        }

    }

    interface BasketListener {
        fun deleteFromBasket(basketModel: BasketModel)
    }

    interface FlowerListener {
        fun onFlowerClick(flowers: ArtModel)
    }
}