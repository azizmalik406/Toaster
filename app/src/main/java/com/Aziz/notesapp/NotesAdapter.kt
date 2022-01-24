package com.Aziz.notesapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.realm.Realm
import io.realm.RealmResults

class NotesAdapter(val context: Context?, private val todoList: RealmResults<Notes>): RecyclerView.Adapter<NotesAdapter.ViewHolder>() {
lateinit var  realm:Realm

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.notes_rv_layout, parent, false)
        return ViewHolder(v)

    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    class ViewHolder(v: View?) : RecyclerView.ViewHolder(v!!), View.OnClickListener {
        override fun onClick(v: View?) {
        }
        init {
            itemView.setOnClickListener(this)
        }
        val title = itemView.findViewById<TextView>(R.id.titleTV)
        val desc = itemView.findViewById<TextView>(R.id.descTV)
        val id = itemView.findViewById<TextView>(R.id.idNumber)
        val delete = itemView.findViewById<ImageView>(R.id.delete)
        val update = itemView.findViewById<ImageView>(R.id.update)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = todoList[position]!!.title
        holder.desc.text = todoList[position]!!.description
        holder.id.text = todoList[position]!!.id.toString()

        holder.delete.setOnClickListener {
            delete_record(position)
        }
        holder.update.setOnClickListener {
            update_record(position,holder)
        }
    }

    private fun delete_record(position: Int) {
        realm= Realm.getDefaultInstance()
        realm.executeTransaction {
            var results:RealmResults<Notes> = it.where(Notes::class.java).equalTo("id",todoList.get(position)?.id ).findAll()
              results.deleteAllFromRealm()
         }
        notifyDataSetChanged()
    }
    private fun update_record(position: Int,holder:ViewHolder) {
        realm= Realm.getDefaultInstance()
        realm.executeTransaction {
            var notes: Notes? =
                it.where(Notes::class.java).equalTo("id", todoList.get(position)?.id).findFirst()

            if (notes!=null){
                notes.title=holder.title.text.toString()
                notes.description=holder.desc.text.toString()
            }
           // realm.copyToRealmOrUpdate(results)
            notifyDataSetChanged()
        }
    }

}