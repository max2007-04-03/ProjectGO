package ua.opnu.project.mapper;

import org.springframework.stereotype.Component;
import ua.opnu.project.dto.NoteDto;
import ua.opnu.project.entity.Note;

@Component
public class NoteMapper {
    public NoteDto toDto(Note entity) {
        NoteDto dto = new NoteDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        return dto;
    }

    public Note toEntity(NoteDto dto) {
        Note entity = new Note();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        return entity;
    }
}