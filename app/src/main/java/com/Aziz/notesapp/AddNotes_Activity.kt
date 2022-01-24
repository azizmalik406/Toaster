package com.Aziz.notesapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm

class AddNotes_Activity : AppCompatActivity() {
    private lateinit var titleET: EditText
    private lateinit var descET:EditText
    private lateinit var saveBtn: Button
    lateinit var realm: Realm
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)

        realm=Realm.getDefaultInstance()

        //init views

        titleET = findViewById(R.id.titleET)
        descET = findViewById(R.id.descET)
        saveBtn = findViewById(R.id.saveBtn)

        // click listner

        saveBtn.setOnClickListener { addnotes_to_db() }
    }
    fun addnotes_to_db(){

        try {
            realm.executeTransaction(object : Realm.Transaction{
                override fun execute(realm: Realm) {

                    var current_id_number:Number?=realm.where(Notes::class.java).max("id")

                    var maxid:Int
                    maxid= if (current_id_number==null){
                        1
                    }else {
                        current_id_number.toInt() +  1
                    }

                    var notes=Notes()
                    notes.id=maxid
                    notes.description=descET.text.toString()
                    notes.title=titleET.text.toString()

                    realm.copyToRealmOrUpdate(notes)
                    Toast.makeText(
                        this@AddNotes_Activity,
                        "Note added successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(Intent(this@AddNotes_Activity,MainActivity::class.java))
                    finish()
                 }

            })

        }catch (e:Exception ){
            Toast.makeText(
                this@AddNotes_Activity,
                "failed "+e.message,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}