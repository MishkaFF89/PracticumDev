package com.github.mishkaff89.practicumdev

import android.app.Activity.RESULT_OK
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.github.mishkaff89.practicumdev.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentProfileBinding.inflate(
            layoutInflater,
            container,
            false,
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        binding.profilePhotoHolder.setOnClickListener {
            showAlertDialog()
        }
    }

    private fun showAlertDialog() {
        val dialog = ProfileAlertDialog(requireContext())
        dialog.showDialog()
        dialog.onItemClick { clickId ->
            when (clickId) {
                R.id.click_upload -> {
                    startGalleryForIntent()
                }

                R.id.click_take_photo -> {
                    dispatchTakePictureIntent()
                }

                R.id.click_delete -> {
                    binding.ivPhotoProfile.visibility = View.INVISIBLE
                }
            }
        }
    }

    private val startCameraForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                val image = result.data?.extras?.get("data") as Bitmap
                binding.ivPhotoProfile.setImageBitmap(image)
            }
        }

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startCameraForResult.launch(takePictureIntent)
        } catch (e: ActivityNotFoundException) {
            Log.e("Error", e.message.toString())
        }
    }

    private fun startGalleryForIntent() {
        val galleryIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.INTERNAL_CONTENT_URI
        )
        try {
            startGalleryForResult.launch(galleryIntent)
        } catch (e: ActivityNotFoundException) {
            Log.e("", e.message.orEmpty())
        }

    }

    private val startGalleryForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                val image = result.data?.data
                binding.ivPhotoProfile.setImageURI(image)
            }
        }

}
