package com.sherlock.gb.kotlin.intentprocess

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sherlock.gb.kotlin.intentprocess.databinding.FragmentMainBinding

private const val KEY_WAVE_SERVICE_BROADCAST = "myaction_way"
private const val KEY_WAVE_SERVICE_BROADCAST_MESSAGE = "message"

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get(){
            return _binding!!
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        requireContext().unregisterReceiver(receiver)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            intent?.let {
                it.getStringExtra(KEY_WAVE_SERVICE_BROADCAST_MESSAGE)?.let{
                    binding.textEcho.text = it
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireContext().registerReceiver(receiver,
            IntentFilter(KEY_WAVE_SERVICE_BROADCAST)
        )
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}