package br.com.manoelseabra.dailynotes.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.manoelseabra.dailynotes.R;
import br.com.manoelseabra.dailynotes.model.Note;
import br.com.manoelseabra.dailynotes.utils.Utils;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    public static class NoteViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView content;
        private CardView card;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            this.title = itemView.findViewById(R.id.tv_title);
            this.content = itemView.findViewById(R.id.tv_content);
            this.card = itemView.findViewById(R.id.card_note);
        }

        public TextView getTitle() {
            return title;
        }

        public TextView getContent() {
            return content;
        }

        public CardView getCard() {
            return card;
        }
    }

    private final ArrayList<Note> notes = new ArrayList<>();

    public NoteAdapter() {
        this.notes.add(new Note(0, "Recursos", "Para editar uma nota, clique sobre ela.\nCaso queira mudar " +
                "a cor da nota, na lista compacta, clique e segure em alguma das notas.\nPara" +
                " adicionar novas nota, selecione o botão com '+' no canto inferior direito.",
                Color.WHITE));
    }

    public Note getNotesByPosition(int position) {
        return notes.get(position);
    }

    public void insertNote(Note note) {
        this.notes.add(note);
        this.notifyItemInserted(notes.indexOf(note));
    }

    public void removeNote(Note note) {
        int position = this.notes.indexOf(note);
        this.notes.remove(note);
        this.notifyItemRemoved(position);
    }

    public void updateNote(Note note, String title, String content) {
        int index = notes.indexOf(note);

        note.setTitle(title);
        note.setContent(content);

        this.notes.set(index, note);
        this.notifyItemChanged(index);
    }

    public Note createEmptyNote() {
        int id;

        if(this.notes.size() == 0)
            id = 0;
        else
            id = this.notes.get(this.notes.size() - 1).getId() + 1;

        Note newNote = new Note(id);

        this.insertNote(newNote);

        return newNote;
    }

    public void cycleColorFromNote(int position) {
        Note note = this.notes.get(position);

        note.setColor(Utils.getNextColor(note.getColor()));

        notes.set(position, note);

        this.notifyItemChanged(position);
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);

        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.getTitle().setText(this.notes.get(position).getTitle());
        holder.getContent().setText(this.notes.get(position).getContent());
        holder.getCard().setBackgroundColor(this.notes.get(position).getColor());
    }

    @Override
    public int getItemCount() {
        return this.notes.size();
    }
}
