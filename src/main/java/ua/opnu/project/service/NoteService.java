package ua.opnu.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.opnu.project.entity.Note;
import ua.opnu.project.repository.NoteRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;


    public Note create(Note note) {
        return noteRepository.save(note);
    }


    public List<Note> getAll() {
        return noteRepository.findAll();
    }


    public Note getById(Long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found with id: " + id));
    }

    public Note update(Long id, Note updatedNoteData) {
        Note existingNote = getById(id); 
        existingNote.setTitle(updatedNoteData.getTitle());
        existingNote.setContent(updatedNoteData.getContent());

        return noteRepository.save(existingNote);
    }

    public void delete(Long id) {
        if (!noteRepository.existsById(id)) {
            throw new RuntimeException("Note not found with id: " + id);
        }
        noteRepository.deleteById(id);
    }
}
