package com.wordpress.lochindev.ramazon_taqvimi.ui.fragment

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wordpress.lochindev.ramazon_taqvimi.R
import com.wordpress.lochindev.ramazon_taqvimi.utilits.ValueStatic
import com.wordpress.lochindev.ramazon_taqvimi.databinding.FragmentDuolarBinding

class DuolarFragment : Fragment() {

    private var _binding: FragmentDuolarBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView
    private val binding get() = _binding!!

    lateinit var saxarlikMediaPlayer: MediaPlayer;
    lateinit var iftorlikMediaPlayer: MediaPlayer;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDuolarBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.cardSaharlik.setOnClickListener {

            volumeFunStopIftorlik()

            if (!ValueStatic.bool_saharlik) {

                saxarlikMediaPlayer = MediaPlayer.create(requireActivity(), R.raw.saharlik);
                binding.imgSaharlikSound.setImageResource(R.drawable.ic_pause)
                try {
                    saxarlikMediaPlayer.start()
                } catch (e: Exception) {
                }
                ValueStatic.bool_saharlik = true

            } else {

                binding.imgSaharlikSound.setImageResource(R.drawable.ic_volume)
                try {
                    saxarlikMediaPlayer.stop()
                } catch (e: Exception) {
                }
                ValueStatic.bool_saharlik = false
            }


        }

        binding.cardIftorlik.setOnClickListener {
            volumeFunStopSaharlik()

            if (!ValueStatic.bool_iftorlik) {

                iftorlikMediaPlayer = MediaPlayer.create(requireActivity(), R.raw.iftorlik);
                binding.imgIftorlikSound.setImageResource(R.drawable.ic_pause)
                try {
                    iftorlikMediaPlayer.start()
                } catch (e: Exception) {
                }
                ValueStatic.bool_iftorlik = true

            } else {

                binding.imgIftorlikSound.setImageResource(R.drawable.ic_volume)
                try {
                    iftorlikMediaPlayer.stop()
                } catch (e: Exception) {
                }
                ValueStatic.bool_iftorlik = false
            }
        }


        return root
    }

    companion object {
      @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DuolarFragment().apply {

            }
    }
    override fun onPause() {
        volumeFunStopIftorlik();
        volumeFunStopSaharlik();
        super.onPause()
    }

    override fun onResume() {
        volumeFunStopIftorlik();
        volumeFunStopSaharlik();
        super.onResume()
    }

    private fun volumeFunStopIftorlik() {
        ValueStatic.bool_iftorlik = false

        try {
            binding.imgIftorlikSound.setImageResource(R.drawable.ic_volume)
            iftorlikMediaPlayer.stop()
        } catch (e: Exception) {
        }

    }

    private fun volumeFunStopSaharlik() {
        ValueStatic.bool_saharlik = false

        try {
            binding.imgSaharlikSound.setImageResource(R.drawable.ic_volume)
            saxarlikMediaPlayer.stop()
        } catch (e: Exception) {
        }
    }


    override fun onDestroyView() {

        volumeFunStopIftorlik();
        volumeFunStopSaharlik();
        super.onDestroyView()
        _binding = null
    }
}