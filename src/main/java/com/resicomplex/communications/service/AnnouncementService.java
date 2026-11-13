package com.resicomplex.communications.service;

import com.resicomplex.communications.dto.AnnouncementResponse;
import com.resicomplex.communications.dto.PublishAnnouncementRequest;
import com.resicomplex.communications.dto.UpdateAnnouncementRequest;
import com.resicomplex.communications.model.Announcement;
import com.resicomplex.communications.repository.AnnouncementRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AnnouncementService implements IAnnouncementService {

    private final AnnouncementRepository repository;

    public AnnouncementService(AnnouncementRepository repository) {
        this.repository = repository;
    }

    @Override
    public AnnouncementResponse publish(PublishAnnouncementRequest request) {
        Announcement a = new Announcement();
        a.setId(UUID.randomUUID().toString());
        a.setTitle(request.title());
        a.setContent(request.content());
        a.setTargetScope(request.targetScope());
        a.setPublishedBy(request.publishedBy());
        a.setStatus("PUBLISHED");
        a.setCreatedAt(LocalDateTime.now());
        return toResponse(repository.save(a));
    }

    @Override
    public List<AnnouncementResponse> findAll(String unitType) {
        List<Announcement> items;
        if (unitType == null || unitType.isBlank()) {
            items = repository.findAllByOrderByCreatedAtDesc();
        } else if ("COMMERCIAL".equalsIgnoreCase(unitType)) {
            items = repository.findByTargetScopeInOrderByCreatedAtDesc(List.of("ALL", "ONLY_COMMERCIAL"));
        } else if ("RESIDENTIAL".equalsIgnoreCase(unitType)) {
            items = repository.findByTargetScopeInOrderByCreatedAtDesc(List.of("ALL", "ONLY_RESIDENTIAL"));
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "unitType must be COMMERCIAL or RESIDENTIAL");
        }
        return items.stream().map(this::toResponse).toList();
    }

    @Override
    public AnnouncementResponse findById(String id) {
        return toResponse(getOrThrow(id));
    }

    @Override
    public AnnouncementResponse update(String id, UpdateAnnouncementRequest request) {
        Announcement a = getOrThrow(id);
        a.setTitle(request.title());
        a.setContent(request.content());
        a.setTargetScope(request.targetScope());
        return toResponse(repository.save(a));
    }

    @Override
    public void delete(String id) {
        repository.delete(getOrThrow(id));
    }

    private Announcement getOrThrow(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Announcement not found"));
    }

    private AnnouncementResponse toResponse(Announcement a) {
        return new AnnouncementResponse(a.getId(), a.getTitle(), a.getContent(), a.getTargetScope(),
                a.getPublishedBy(), a.getStatus(), a.getCreatedAt());
    }
}
