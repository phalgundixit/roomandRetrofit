package com.example.roomandretrofit.Room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

//this tells room that this interface is dao,generally we should create one dao for one entity
@Dao
public interface NoteDao {
    //will automatically handle inserting values into the table
    //we pass the values as arguments
    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    //if there is no inbulit annotation for the operations u need to do
    //use @Query and write the query as to what u want to do
    @Query("DELETE FROM note_table")
    void delete_all();

    //live data can be observed i.e. as soon as there is any change in the data activity will be updated and ui will be updated
    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    LiveData<List<Note>> getAllNotes();
}
