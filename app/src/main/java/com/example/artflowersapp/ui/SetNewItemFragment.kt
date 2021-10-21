package com.example.artflowersapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.artflowersapp.databinding.FragmentSetNewItemBinding
import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.example.artflowersapp.R
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

            //viewModel.insertNewItem(newArtModel)
            alertDialog(newArtModel)
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

    fun alertDialog(artModel: ArtModel) {

        val builder = AlertDialog.Builder(context)
        //set title for alert dialog
        builder.setTitle(R.string.dialog_title_set_new)
        //set message for alert dialog
        builder.setMessage(R.string.dialog_message_set_new)
        builder.setIcon(R.drawable.ic_baseline_add_24)

        //performing positive action
        builder.setPositiveButton(R.string.ad_yes_set_new) { dialogInterface, which ->
            viewModel.insertNewItem(artModel)
            Toast.makeText(context,"Продукт опубликован", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.personFragment)
        }
        //performing negative action
        builder.setNegativeButton(R.string.ad_no) { dialogInterface, which ->
            dialogInterface.cancel()
        }
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()
    }




}