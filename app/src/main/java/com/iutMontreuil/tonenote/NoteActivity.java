package com.iutMontreuil.tonenote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NoteActivity extends AppCompatActivity {

    EditText tp;
    private String text;

       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        tp = (EditText) findViewById (R.id.note_edittext_name);
        loadGameSetting();
    }

    public void doSave(View view)  {
        // The created file can only be accessed by the calling application
        // (or all applications sharing the same user ID).
        SharedPreferences sharedPreferences= this.getSharedPreferences("texte", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("leTexte", this.tp.getText().toString());
        // Save.
        editor.apply();
        Toast.makeText(this, "data saved", Toast.LENGTH_SHORT).show();
    }

    private void loadGameSetting()  {
        SharedPreferences sharedPreferences = getSharedPreferences("texte", Context.MODE_PRIVATE);
        if(sharedPreferences!= null) {
            text = sharedPreferences.getString("leTexte", "");
            tp.setText(text);
        }
    }

    protected void onStop(){
           super.onStop();
           doSave(tp);
    }
}