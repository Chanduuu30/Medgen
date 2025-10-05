package com.example.medgen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.medgen.data.HistoryDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

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

        val historyDao = HistoryDatabase.getDatabase(requireContext()).historyDao()

        // Camera click
        cameraBtn.setOnClickListener {
            Toast.makeText(requireContext(), "Opening camera...", Toast.LENGTH_SHORT).show()

            // TODO: launch camera and process image
            val output = "AI detected: Paracetamol 500mg\nUsage: Twice daily after meals"
            resultText.text = output

            // ✅ Save to local DB (Step 5)
            lifecycleScope.launch {
                val history = History(
                    type = "scan",
                    inputText = "Prescription Image",
                    resultText = output,
                    timestamp = System.currentTimeMillis()
                )
                historyDao.insert(history)
            }
        }

        // Language change click
        changeLangBtn.setOnClickListener {
            Toast.makeText(requireContext(), "Switching language...", Toast.LENGTH_SHORT).show()
            // TODO: integrate translation feature
        }

        return view
    }
}
