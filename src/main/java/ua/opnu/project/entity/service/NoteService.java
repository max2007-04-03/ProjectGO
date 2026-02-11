package ua.opnu.project.entity.service;

import ua.opnu.project.entity.dto.NoteDto ;
import ua.opnu.project.entity.Note ;
import ua.opnu.project.entity.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteDto create(NoteDto dto) {
        Note note = new Note();
        note.setTitle(dto.getTitle());
        note.setContent(dto.getContent());

        Note savedNote = noteRepository.save(note);
        return mapToDto(savedNote);
    }

    public NoteDto getById(Long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found with id: " + id));
        return mapToDto(note);
    }

    public List<NoteDto> getAll() {
        return noteRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public NoteDto update(Long id, NoteDto dto) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        note.setTitle(dto.getTitle());
        note.setContent(dto.getContent());

        Note updatedNote = noteRepository.save(note);
        return mapToDto(updatedNote);
    }

    public void delete(Long id) {
        if (!noteRepository.existsById(id)) {
            throw new RuntimeException("Note not found");
        }
        noteRepository.deleteById(id);
    }

    private NoteDto mapToDto(Note note) {
        NoteDto dto = new NoteDto();
        dto.setId(note.getId());
        dto.setTitle(note.getTitle());
        dto.setContent(note.getContent());
        return dto;
    }
}