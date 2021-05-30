package com.intproj

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.intproj.shared.navigation.Navigator

class NavigationActivity : AppCompatActivity() {

    val navigator by lazy { Navigator(supportFragmentManager, R.id.nvContainer) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        navigator.openPeopleFragment()
    }
}