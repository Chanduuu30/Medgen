package com.example.medgen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val bottomNav: BottomNavigationView = findViewById(R.id.bottomNav)

        // ✅ Load HomeFragment by default
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, HomeFragment())
                .commit()
            bottomNav.selectedItemId = R.id.nav_home  // highlight Home in bottom nav
        }

        // Handle bottom nav clicks
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, HomeFragment())
                        .commit()
                }
                R.id.nav_scan -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, ScanFragment())
                        .commit()
                }
                R.id.nav_symptoms -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, SymptomsFragment())
                        .commit()
                }
                R.id.nav_alerts -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, AlertsFragment())
                        .commit()
                }
            }
            true
        }
    }
}
