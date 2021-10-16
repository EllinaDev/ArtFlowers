package com.example.artflowersapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.artflowersapp.databinding.FragmentSetNewItemBinding
import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.fragment.app.viewModels
import com.example.artflowersapp.data.ArtModel
import com.example.artflowersapp.viewModel.SetNewItemVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SetNewItemFragment : Fragment() {

    private var imageUri: String? = null
    private val SELECT_SINGLE_PICTURE = 101
    private val viewModel: SetNewItemVM by viewModels()

    private var _binding: FragmentSetNewItemBinding? = null
    private val binding: FragmentSetNewItemBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSetNewItemBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivAddPhoto.setOnClickListener {
            val intent = Intent()
            intent.setType("image/*")
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(
                    intent,
                    "Choose your image"
                ), SELECT_SINGLE_PICTURE
            )
        }

        binding.btnReady.setOnClickListener{
            val newArtModel = ArtModel(
                name = binding.etAddName.text.toString(),
                price = binding.etAddPrice.text.toString().toInt(),
                description = binding.etAddDescription.text.toString(),
                composition = binding.etAddComposition.text.toString(),
                size = binding.etAddSize.text.toString(),
                photoUri = imageUri
            )

            viewModel.insertNewItem(newArtModel)
        }



    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_SINGLE_PICTURE) {
                val image = data?.data // Uri
                imageUri = image.toString()
                //val imageBitmap = MediaStore.Images.Media.getBitmap(activity?.contentResolver, image)
                binding.ivAddPhoto.setImageURI(image)
            }
        }
    }





}