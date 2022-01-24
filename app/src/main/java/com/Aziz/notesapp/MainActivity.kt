package com.Aziz.notesapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.realm.Realm
import io.realm.RealmResults

class MainActivity : AppCompatActivity(){
    lateinit var rc_notes:RecyclerView
    lateinit var add_note:FloatingActionButton
    lateinit var notes_array:ArrayList<Notes>
    lateinit var realm: Realm
    lateinit var status:TextView
    lateinit var subscribr_icon:ImageView

    ///Billing
    //lateinit var bp:BillingProcessor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        setup_view()
        onclick()
        getallnotes()
    }
    fun setup_view(){
        /* bp = BillingProcessor(this, resources.getString(R.string.license_key), this)
        bp.initialize()*/
    }
    fun init(){
        rc_notes=findViewById(R.id.rc_notes)
        add_note=findViewById(R.id.add_note)
        status=findViewById(R.id.status)
        subscribr_icon=findViewById(R.id.subscribr_icon)

        realm= Realm.getDefaultInstance()
        rc_notes.layoutManager=StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
    }
    fun onclick(){
        add_note.setOnClickListener {
            startActivity(Intent(this, AddNotes_Activity::class.java))
            finish()
        }
    }

    fun getallnotes(){
         notes_array= ArrayList()

        var result:RealmResults<Notes> = realm.where(Notes::class.java).findAll()

        var adapter=NotesAdapter(this,result)
        rc_notes.adapter=adapter
        adapter.notifyDataSetChanged()

    }

}