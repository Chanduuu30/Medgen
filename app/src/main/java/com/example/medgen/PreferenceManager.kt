package com.example.medgen

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object PreferenceManager {

    private const val PREF_NAME = "MedGenPrefs"
    private const val KEY_SYMPTOMS = "symptom_history"

    fun saveSymptom(context: Context, symptom: String) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val gson = Gson()
        val existing = getSymptoms(context).toMutableList()
        existing.add(0, symptom) // Add latest first
        prefs.edit().putString(KEY_SYMPTOMS, gson.toJson(existing)).apply()
    }

    fun getSymptoms(context: Context): List<String> {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val json = prefs.getString(KEY_SYMPTOMS, null) ?: return emptyList()
        val type = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(json, type)
    }
}
