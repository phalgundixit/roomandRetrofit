package com.example.roomandretrofit.Room;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.roomandretrofit.R;

public class AddNote extends AppCompatActivity {

    public static final String EXTRA_TITLE = "com.example.roomandretrofit.Room.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.example.roomandretrofit.Room.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY = "com.example.roomandretrofit.Room.EXTRA_PRIORITY";

    private EditText editTexetTitle;
    private EditText editTextDescription;
    private NumberPicker numberPickerPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTexetTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);

        numberPickerPriority = findViewById(R.id.number_picker_priority);
        numberPickerPriority.setMaxValue(10);
        numberPickerPriority.setMinValue(1);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Note");
    }
    private void saveNote(){
        String Title = editTexetTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        int priority = numberPickerPriority.getValue();

        if (Title.trim().isEmpty() || description.trim().isEmpty()){
            Toast.makeText(this, "Enter Title and Description", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_TITLE,Title);
        intent.putExtra(EXTRA_DESCRIPTION,description);
        intent.putExtra(EXTRA_PRIORITY,priority);

        setResult(RESULT_OK,intent);
        finish();




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
