package com.example.roomandretrofit.Room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

//---- how to create this class -----
/*create new java class, type RoomDatabase in superclass field(it has to extend RoomDatabase)
and select the abstract radio  button and press ok.*/


/*entities will tell room what all tables the database should contain,
if u have more tha one tables use {Note.class,book.class}
version is used for version control for migration(similar to sqite)*/

@Database(entities = {Note.class}, version =  1)
public abstract class NoteDatabase extends RoomDatabase {

    //this variable is created to make this class singleton
    // (singleton means we cant create multiple instances of this class, a single instance is used throughout the application)
    private static NoteDatabase instance;

    //this is used to access dao
    public abstract NoteDao noteDao();


    //creating database
    //single database instance is created,synchronized means only one thread at a time can access this method
    public static synchronized NoteDatabase getInstance(Context context){
       // if instance is null instanciate the database,else return the instance received
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),NoteDatabase.class,"note_database")
                    .fallbackToDestructiveMigration().addCallback(roomcallback).build();
        }
        return instance;
    }

    //to populate the database even before opening the app,(used to do in on create of dbHelper class) use callback
    private static RoomDatabase.Callback roomcallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new populateDBAsynctaks(instance).execute();
        }
    };
    private static class populateDBAsynctaks extends AsyncTask<Void,Void,Void>{
        private NoteDao noteDao;
        private populateDBAsynctaks(NoteDatabase db){
            noteDao = db.noteDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("Title1","Descrpition1",1));
            noteDao.insert(new Note("Title2","Descrpition2",2));
            noteDao.insert(new Note("Title3","Descrpition3",3));
            noteDao.insert(new Note("Title4","Descrpition4",4));
            return null;
        }
    }
}
