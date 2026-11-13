package com.resicomplex.communications.repository;

import com.resicomplex.communications.model.Announcement;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementRepository extends JpaRepository<Announcement, String> {

    List<Announcement> findAllByOrderByCreatedAtDesc();

    List<Announcement> findByTargetScopeInOrderByCreatedAtDesc(List<String> scopes);
}
