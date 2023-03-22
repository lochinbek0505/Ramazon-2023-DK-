package com.wordpress.lochindev.ramazon_taqvimi.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.wordpress.lochindev.ramazon_taqvimi.databinding.FragmentSettingsBinding
import com.wordpress.lochindev.ramazon_taqvimi.ui.activity.LocationActivity

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val root: View = binding.root



        binding.cvLocation.setOnClickListener {

            val intent=Intent(requireActivity(),LocationActivity::class.java)

            intent.putExtra("BACK",1)

            startActivity(intent)

            requireActivity().finish()


        }

        binding.ivTelegram.setOnClickListener {

            gotoUri("https://t.me/dasturchilarklubi")

        }

        binding.ivWeb.setOnClickListener {

            gotoUri("https://youtube.com/@DasturchilarKlubi")

        }

        binding.ivInstagramm.setOnClickListener {
            gotoUri("https://instagram.com/dasturchilarklubi")

        }

            binding.aloqaCall.setOnClickListener {
            val intent= Intent(Intent.ACTION_DIAL)
            intent.data= Uri.parse("tel:"+"+998934940765")
            startActivity(intent)
        }

        return root
    }
    private fun gotoUri(uri: String) {
        val a: Uri = Uri.parse(uri)
        startActivity(Intent(Intent.ACTION_VIEW, a))
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}