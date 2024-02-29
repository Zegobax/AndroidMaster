package com.biodan997.androidmaster.Apps.IMCalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.biodan997.androidmaster.OrganizerApp.OrganizerActivity
import com.biodan997.androidmaster.R
import com.biodan997.androidmaster.exercise.AccountOrganizerApp.account_organizerActivity


class Manuctivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manuctivity)
        val btnAccountApp = findViewById<Button>(R.id.btnAccountApp)
        val btnImcApp = findViewById<Button>(R.id.btnIMCApp)
        val btnTasksApp = findViewById<Button>(R.id.btnTasksApp)
        btnImcApp.setOnClickListener { navigateToImcApp() }
        btnAccountApp.setOnClickListener { navigateAccountApp() }
        btnTasksApp.setOnClickListener { navigateTasksApp() }
    }

    private fun navigateToImcApp() {
        val intent = Intent(this, IMCalculator_Activity::class.java)
        startActivity(intent)
    }

    private fun navigateAccountApp() {

        val intent = Intent(this, account_organizerActivity::class.java)
        startActivity(intent)

    }

    private fun navigateTasksApp() {

        val intent = Intent(this, OrganizerActivity::class.java)
        startActivity(intent)

    }


}

