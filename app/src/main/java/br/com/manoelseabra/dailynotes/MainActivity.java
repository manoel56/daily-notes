package br.com.manoelseabra.dailynotes;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.manoelseabra.dailynotes.adapters.NoteAdapter;
import br.com.manoelseabra.dailynotes.model.Note;
import br.com.manoelseabra.dailynotes.utils.RecyclerItemClickListener;

public class MainActivity extends AppCompatActivity {

    private NoteAdapter adapter;
    private Note currentlyOpenedNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(v -> createNote());

        final RecyclerView rv = findViewById(R.id.rv_notes);
        rv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new NoteAdapter();
        rv.setAdapter(adapter);

        // Adiciona o listener do evento que ocorre ao clicar em qualquer um dos itens da lista
        rv.addOnItemTouchListener(
            new RecyclerItemClickListener(this, rv, new RecyclerItemClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    openNote(adapter.getNotesByPosition(position));
                }

                @Override
                public void onLongItemClick(View view, int position) {
                    adapter.cycleColorFromNote(position);
                }
            })
        );
    }

    public void openNote(Note note) {
        Intent intent = new Intent(this, NoteActivity.class);
        currentlyOpenedNote = note;
        intent.putExtra(NoteActivity.EXTRA_TITLE, currentlyOpenedNote.getTitle());
        intent.putExtra(NoteActivity.EXTRA_CONTENT, currentlyOpenedNote.getContent());
        mStartForResult.launch(intent);
    }

    public void createNote() {
        Intent intent = new Intent(this, NoteActivity.class);
        currentlyOpenedNote = this.adapter.createEmptyNote();
        intent.putExtra(NoteActivity.EXTRA_TITLE, currentlyOpenedNote.getTitle());
        intent.putExtra(NoteActivity.EXTRA_CONTENT, currentlyOpenedNote.getContent());
        mStartForResult.launch(intent);
    }

    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        Bundle extras = data.getExtras();

                        String title = extras.getString(NoteActivity.EXTRA_TITLE);
                        String content = extras.getString(NoteActivity.EXTRA_CONTENT);
                        adapter.updateNote(currentlyOpenedNote, title, content);
                    }

                    if (result.getResultCode() == NoteActivity.RESULT_DELETE) {
                        adapter.removeNote(currentlyOpenedNote);
                    }
                }
            });

}