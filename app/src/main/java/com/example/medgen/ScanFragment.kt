package com.example.medgen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        cameraBtn.setOnClickListener {
            Toast.makeText(requireContext(), "Camera clicked!", Toast.LENGTH_SHORT).show()
            // TODO: open camera intent here
        }

        return view
    }
}
