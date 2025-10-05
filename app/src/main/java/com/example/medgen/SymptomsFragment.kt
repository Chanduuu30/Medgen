package com.example.medgen

import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.medgen.data.HistoryDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch
import java.util.*



class SymptomsFragment : Fragment() {

    private lateinit var symptomInput: EditText
    private lateinit var symptomResult: TextView
    private val REQUEST_CODE_SPEECH = 100

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_symptoms, container, false)

        symptomInput = view.findViewById(R.id.symptomInput)
        symptomResult = view.findViewById(R.id.symptomResult)
        val micBtn: FloatingActionButton = view.findViewById(R.id.btnMic)
        val submitBtn: Button = view.findViewById(R.id.btnSubmitSymptoms)

        val historyDao = HistoryDatabase.getDatabase(requireContext()).historyDao()

        // Handle mic input
        micBtn.setOnClickListener {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak your symptoms")
            try {
                startActivityForResult(intent, REQUEST_CODE_SPEECH)
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Speech not supported", Toast.LENGTH_SHORT).show()
            }
        }

        // Handle submit
        submitBtn.setOnClickListener {
            val symptoms = symptomInput.text.toString().trim()
            if (symptoms.isEmpty()) {
                Toast.makeText(requireContext(), "Please enter or speak symptoms", Toast.LENGTH_SHORT).show()
            } else {
                symptomResult.text = "You entered: $symptoms"

                // ✅ Save to local DB
                lifecycleScope.launch {
                    val history = History(
                        type = "symptom",
                        inputText = symptoms,
                        resultText = "User symptoms recorded",
                        timestamp = System.currentTimeMillis()
                    )
                    historyDao.insert(history)
                }
            }
        }

        return view
    }

    // Get result from speech recognizer
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SPEECH && data != null) {
            val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            if (!result.isNullOrEmpty()) {
                symptomInput.setText(result[0])
            }
        }
    }
}
