package com.example.artflowersapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.artflowersapp.R
import com.example.artflowersapp.data.ArtModel
import com.example.artflowersapp.databinding.ItemFlowerBinding
import javax.inject.Inject

class ArtRecyclerView : RecyclerView.Adapter<ArtRecyclerView.ArtViewHolder>() {

    private var flowers: List<ArtModel> = ArrayList()

    fun submitItems(flowers: List<ArtModel>){
        this.flowers = flowers
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtViewHolder {
        return ArtViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ArtViewHolder, position: Int) {
        holder.bind(flowers[position])
    }

    override fun getItemCount(): Int = flowers.size



    class ArtViewHolder @Inject constructor(val binding: ItemFlowerBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(flowers: ArtModel){
        binding.tvName.text = flowers.name
        binding.tvPrice.text = flowers.price.toString()
        binding.ivPhoto.setImageResource(R.drawable.flower)
    }

    companion object{
        fun from(parent: ViewGroup): ArtViewHolder{
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemFlowerBinding.inflate(inflater)
            return ArtViewHolder(binding)
        }
    }
}


}