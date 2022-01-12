package br.com.manoelseabra.dailynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import br.com.manoelseabra.dailynotes.model.Note;

public class NoteActivity extends AppCompatActivity {

    public static String EXTRA_TITLE   = "br.com.manoelseabra.extras.EXTRA_TITLE";
    public static String EXTRA_CONTENT = "br.com.manoelseabra.extras.EXTRA_CONTENT";

    public static int RESULT_DELETE = 5;

    private TextInputEditText title;
    private TextInputEditText content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        // Sets UI elements
        this.title = this.findViewById(R.id.tv_title);
        this.content = this.findViewById(R.id.tv_content);

        final ImageView back = this.findViewById(R.id.button_back);
        back.setOnClickListener(v -> {
            sendResult();
        });

        final ImageView delete = this.findViewById(R.id.btn_delete);
        delete.setOnClickListener(v -> {
            deleteNote();
        });

        Bundle extras = getIntent().getExtras();

        String title = extras.getString(NoteActivity.EXTRA_TITLE);
        String content = extras.getString(NoteActivity.EXTRA_CONTENT);
        this.title.setText(title);
        this.content.setText(content);
    }

    @Override
    protected void onNewIntent(Intent intent) {

        super.onNewIntent(intent);
    }

    private void sendResult() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_TITLE, this.title.getText().toString());
        resultIntent.putExtra(EXTRA_CONTENT, this.content.getText().toString());
        setResult(RESULT_OK, resultIntent);

        NoteActivity.super.onBackPressed();
    }

    private void deleteNote() {
        setResult(RESULT_DELETE);
        NoteActivity.super.onBackPressed();
    }
}