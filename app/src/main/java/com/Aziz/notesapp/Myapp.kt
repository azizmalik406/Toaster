package com.Aziz.notesapp

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class Myapp :Application (){
    override fun onCreate() {
        super.onCreate()

        //init realm
         Realm.init(applicationContext)

        var configuration =RealmConfiguration.Builder()
            .name("notes")
            .deleteRealmIfMigrationNeeded()
            .schemaVersion(0)
            .allowWritesOnUiThread(true)
            .build()

        Realm.setDefaultConfiguration(configuration)

    }
}