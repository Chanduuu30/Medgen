package com.example.medgen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ScanFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_scan, container, false)

        val cameraBtn: FloatingActionButton = view.findViewById(R.id.btnCamera)
        val resultText: TextView = view.findViewById(R.id.resultText)
        val changeLangBtn: Button = view.findViewById(R.id.btnChangeLang)

        // Camera click
        cameraBtn.setOnClickListener {
            Toast.makeText(requireContext(), "Opening camera...", Toast.LENGTH_SHORT).show()
            // TODO: launch camera and process image
            resultText.text = "AI detected: Paracetamol 500mg\nUsage: Twice daily after meals"
        }

        // Language change click
        changeLangBtn.setOnClickListener {
            Toast.makeText(requireContext(), "Switching language...", Toast.LENGTH_SHORT).show()
            // TODO: integrate translation feature
        }

        return view
    }
}
