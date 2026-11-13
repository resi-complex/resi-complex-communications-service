package com.resicomplex.communications.controller;

import com.resicomplex.communications.dto.AnnouncementResponse;
import com.resicomplex.communications.dto.PublishAnnouncementRequest;
import com.resicomplex.communications.dto.UpdateAnnouncementRequest;
import com.resicomplex.communications.service.IAnnouncementService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/announcements")
public class AnnouncementController {

    private final IAnnouncementService service;

    public AnnouncementController(IAnnouncementService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AnnouncementResponse publish(@Valid @RequestBody PublishAnnouncementRequest request) {
        return service.publish(request);
    }

    @GetMapping
    public List<AnnouncementResponse> list(@RequestParam(name = "unitType", required = false) String unitType) {
        return service.findAll(unitType);
    }

    @GetMapping("/{id}")
    public AnnouncementResponse get(@PathVariable("id") String id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public AnnouncementResponse update(@PathVariable("id") String id,
                                       @Valid @RequestBody UpdateAnnouncementRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        service.delete(id);
    }
}
