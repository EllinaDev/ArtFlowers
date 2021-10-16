package com.example.artflowersapp.ui

import android.R.id
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.artflowersapp.adapter.HomeAdapter
import com.example.artflowersapp.data.ArtModel
import com.example.artflowersapp.databinding.FragmentDetailBinding
import com.example.artflowersapp.utils.FakeData
import android.R.id.message
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide


class DetailFragment: Fragment(), HomeAdapter.FlowerListener, HomeAdapter.FlowerBasketListener {

    private val args: DetailFragmentArgs by navArgs()

    private var _binding: FragmentDetailBinding? = null
    private val binding: FragmentDetailBinding get() = _binding!!
    private val adapter: HomeAdapter = HomeAdapter(this, this)

    private var flower: ArtModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        flower = args.flower
        println("FROM ARGS: $flower")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        adapter.submitItems(FakeData.flowers)

        binding.tvName.text = flower?.name
        binding.tvComposition.text = flower?.composition
        binding.tvSize.text = flower?.size
        binding.tvDescription.text = flower?.description
        binding.tvPrice.text = flower?.price.toString()
        Glide.with(binding.root.context)
            .load(flower?.photoUri)
            .into(binding.ivPhoto)

        binding.ivWhatsapp.setOnClickListener{
            toWhatsapp()
        }
        binding.ivInstagram.setOnClickListener {
            toInstagram()
        }
        binding.ivCall.setOnClickListener {
            toPhone()
        }


    }

    override fun onFlowerClick(flowers: ArtModel) {

    }

    override fun onBasketClick(flowers: ArtModel) {

    }

    private fun toWhatsapp(){
        val phone = flower?.waNumber
        try {
            val packageManager = requireContext().packageManager
            val i = Intent(Intent.ACTION_VIEW)
            val url = "https://api.whatsapp.com/send?phone=" + phone + "&text="
            i.setPackage("com.whatsapp")
            i.setData(Uri.parse(url))
            if (i.resolveActivity(packageManager) != null) {
                requireContext().startActivity(i)
            }
        }catch (e: Exception){
            e.printStackTrace()
            val appPackageName = "com.whatsapp"
        }
    }

    private fun toInstagram(){
        val uri = Uri.parse("http://instagram.com/_u/_smtva_")
        val likeIng = Intent(Intent.ACTION_VIEW, uri)

        likeIng.setPackage("com.instagram.android")

        try {
            startActivity(likeIng)
        } catch (e: ActivityNotFoundException) {
            startActivity(

                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://instagram.com/xxx")
                )
            )
        }
    }

    private fun toPhone(){
        val number = flower?.telNumber
        val call = Uri.parse("tel:$number")
        val surf = Intent(Intent.ACTION_DIAL, call)
        startActivity(surf)
    }


}

