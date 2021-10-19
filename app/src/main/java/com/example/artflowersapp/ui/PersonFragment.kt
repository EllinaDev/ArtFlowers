package com.example.artflowersapp.ui

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.artflowersapp.R
import com.example.artflowersapp.adapter.AccountAdapter
import com.example.artflowersapp.data.ArtModel
import com.example.artflowersapp.databinding.FragmentHomeBinding
import com.example.artflowersapp.databinding.FragmentPersonBinding
import com.example.artflowersapp.viewModel.AccountVM
import com.example.artflowersapp.viewModel.ArtViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PersonFragment: Fragment(), AccountAdapter.AccountFlowerListener, AccountAdapter.AccountDeleteListener {

    private var imageUri: String? = null
    private val SELECT_SINGLE_PICTURE = 101

    private var _binding: FragmentPersonBinding? = null
    private val binding: FragmentPersonBinding get() = _binding!!
    private val adapter: AccountAdapter = AccountAdapter(this,this)
    private val viewModel: AccountVM by viewModels()
    private var flower: ArtModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPersonBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        viewModel.flowersLiveData.observe(viewLifecycleOwner,{
            Log.d("TAG", "ALL: $it")
            adapter.submitItems(it)
        })
        binding.ivItemAccount.setOnClickListener {
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

        toSettings()

    }

    override fun delete(artModel: ArtModel) {
        //viewModel.deleteItem(artModel)
        alertDialog(artModel)
    }

    override fun onFlowerClick(artModel: ArtModel) {
        val directions = PersonFragmentDirections.actionPersonFragmentToDetailFragment(artModel)
        findNavController().navigate(directions)
    }

    private fun toSettings(){
        binding.ivSettings.setOnClickListener {
            it.findNavController().navigate(R.id.settingsAccountFragment)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_SINGLE_PICTURE) {
                val image = data?.data // Uri
                imageUri = image.toString()
                //val imageBitmap = MediaStore.Images.Media.getBitmap(activity?.contentResolver, image)
                binding.ivItemAccount.setImageURI(image)
            }
        }
    }

    fun alertDialog(artModel: ArtModel) {

        val builder = AlertDialog.Builder(context)
        //set title for alert dialog
        builder.setTitle(R.string.dialogTitle)
        //set message for alert dialog
        builder.setMessage(R.string.dialogMessage)
        builder.setIcon(R.drawable.ic_baseline_delete_24)

        //performing positive action
        builder.setPositiveButton(R.string.ad_yes) { dialogInterface, which ->
            viewModel.deleteItem(artModel)
            Toast.makeText(context,"Публикация успешно удалена", Toast.LENGTH_LONG).show()
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