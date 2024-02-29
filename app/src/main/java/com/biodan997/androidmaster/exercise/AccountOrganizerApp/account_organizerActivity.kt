package com.biodan997.androidmaster.exercise.AccountOrganizerApp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.biodan997.androidmaster.R

class account_organizerActivity : AppCompatActivity() {

    internal var dbHelper = DatabaseHelper(this)

    lateinit var platformTxt: EditText
    lateinit var emailTxt: EditText
    lateinit var nameTxt: EditText
    lateinit var idTxt: EditText

    lateinit var insertBtn: Button
    lateinit var updateBtn: Button
    lateinit var deleteBtn: Button
    lateinit var viewBtn: Button


    fun showToast(text: String) {
        Toast.makeText(this@account_organizerActivity, text, Toast.LENGTH_LONG).show()
    }

    fun showDialog(title: String, Message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setTitle(title)
        builder.setMessage(Message)
        builder.show()
    }

    private fun clearEditTexts() {
        platformTxt.setText("")
        emailTxt.setText("")
        nameTxt.setText("")
        idTxt.setText("")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.account_organizer_app)

        platformTxt = findViewById(R.id.platformTxt)
        emailTxt = findViewById(R.id.emailText)
        nameTxt = findViewById(R.id.nameTxt)
        idTxt = findViewById(R.id.idTxt)

        insertBtn = findViewById(R.id.btnInsert)
        updateBtn = findViewById(R.id.btnUpdate)
        deleteBtn = findViewById(R.id.btnDelete)
        viewBtn = findViewById(R.id.btnViewAll)

        handleInsert()
        handleUpdates()
        handleDeletes()
        handleViewing()

    }


    fun handleInsert() {
        insertBtn.setOnClickListener {
            try {
                val isInsert = dbHelper.insertData(
                    platformTxt.text.toString(),
                    emailTxt.text.toString(),
                    nameTxt.text.toString()
                )
                clearEditTexts()
                if (isInsert == true)

                    showToast("DATA INSERT SUCCESFULLY")
                else
                    showToast("DATA CANNOT BE INSERTED")

            } catch (e: Exception) {
                e.printStackTrace()
                showToast(e.message.toString())
            }


        }
    }

    fun handleUpdates() {
        updateBtn.setOnClickListener {
            try {
                val isUpdate = dbHelper.updateData(
                    idTxt.text.toString(),
                    platformTxt.text.toString(),
                    emailTxt.text.toString(),
                    nameTxt.text.toString()
                )
                clearEditTexts()
                if (isUpdate == true)

                    showToast("DATA UPDATE SUCCESFULLY")
                else
                    showToast("DATA CANNOT BE UPDATE")
            } catch (e: Exception) {
                e.printStackTrace()
                showToast(e.message.toString())
            }
        }
    }


    fun handleDeletes() {
        deleteBtn.setOnClickListener {
            try {
                val rowsDeleted = dbHelper.deleteData(idTxt.text.toString())
                clearEditTexts()

                if (rowsDeleted > 0)

                    showToast("DATA DELETE SUCCESFULLY")
                else

                    showToast("DATA CANNOT BE SUCCESFULLY")

            } catch (e: Exception) {
                e.printStackTrace()
                showToast(e.message.toString())
            }
        }
    }


    fun handleViewing() {
        viewBtn.setOnClickListener(
            View.OnClickListener {
                val res = dbHelper.allData
                if (res.count == 0) {
                    showDialog("DATABASE", "NO DATA FOUND!")
                    return@OnClickListener
                }

                val buffer = StringBuffer()
                while (res.moveToNext()) {
                    buffer.append("ID:         " + res.getString(0) + "\n")
                    buffer.append("PLATFORM:   " + res.getString(1) + "\n")
                    buffer.append("EMAIL:      " + res.getString(2) + "\n")
                    buffer.append("NAME:       " + res.getString(3) + "\n")
                    buffer.append("\n")

                }

                showDialog("LISTED DATA", buffer.toString())
            }
        )

    }

}

