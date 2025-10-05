package com.example.medgen

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.medgen.data.HistoryDatabase
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class AlertsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_alerts, container, false)
        val listView: ListView = view.findViewById(R.id.historyList)

        val dao = HistoryDatabase.getDatabase(requireContext()).historyDao()

        lifecycleScope.launch {
            val historyList = dao.getAllHistory()

            val displayList = historyList.map { history ->
                val formattedTime = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                    .format(history.timestamp)

                // Format text properly using HTML so multi-line result stays under Result
                Html.fromHtml(
                    """
                    <b>🔹 ${history.type.uppercase()}</b><br>
                    🩺 <b>Input:</b> ${history.inputText}<br>
                    📋 <b>Result:</b><br>${history.resultText.replace("\n", "<br>")}<br>
                    ⏰ <i>$formattedTime</i>
                    """.trimIndent(),
                    Html.FROM_HTML_MODE_LEGACY
                )
            }

            val adapter = object : ArrayAdapter<CharSequence>(
                requireContext(),
                android.R.layout.simple_list_item_1,
                displayList
            ) {
                override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                    val view = super.getView(position, convertView, parent)
                    // Add padding and spacing for readability
                    view.setPadding(24, 24, 24, 24)
                    return view
                }
            }

            listView.adapter = adapter
        }

        return view
    }
}
