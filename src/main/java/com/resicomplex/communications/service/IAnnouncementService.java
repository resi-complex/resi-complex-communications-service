package com.resicomplex.communications.service;

import com.resicomplex.communications.dto.AnnouncementResponse;
import com.resicomplex.communications.dto.PublishAnnouncementRequest;
import com.resicomplex.communications.dto.UpdateAnnouncementRequest;
import java.util.List;

public interface IAnnouncementService {

    AnnouncementResponse publish(PublishAnnouncementRequest request);

    List<AnnouncementResponse> findAll(String unitType);

    AnnouncementResponse findById(String id);

    AnnouncementResponse update(String id, UpdateAnnouncementRequest request);

    void delete(String id);
}
