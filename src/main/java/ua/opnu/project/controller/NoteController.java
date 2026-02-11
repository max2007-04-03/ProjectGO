package ua.opnu.project.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.opnu.project.dto.NoteDto;
import ua.opnu.project.entity.Note;
import ua.opnu.project.mapper.NoteMapper;
import ua.opnu.project.service.NoteService;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;
    private final NoteMapper noteMapper;

    @PostMapping
    public ResponseEntity<NoteDto> createNote(@Valid @RequestBody NoteDto noteDto) {
        // Конвертуємо DTO в Entity для сервісу
        Note note = noteMapper.toEntity(noteDto);
        Note savedNote = noteService.create(note);
        // Конвертуємо результат назад у DTO для клієнта
        return ResponseEntity.status(HttpStatus.CREATED).body(noteMapper.toDto(savedNote));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteDto> getNote(@PathVariable Long id) {
        Note note = noteService.getById(id);
        return ResponseEntity.ok(noteMapper.toDto(note));
    }

    @GetMapping
    public ResponseEntity<List<NoteDto>> getAllNotes() {
        // Використовуємо твій підхід зі Stream API для мапінгу списку
        List<NoteDto> dtos = noteService.getAll().stream()
                .map(noteMapper::toDto)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteDto> updateNote(@PathVariable Long id, @Valid @RequestBody NoteDto noteDto) {
        Note noteData = noteMapper.toEntity(noteDto);
        Note updatedNote = noteService.update(id, noteData);
        return ResponseEntity.ok(noteMapper.toDto(updatedNote));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        noteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}