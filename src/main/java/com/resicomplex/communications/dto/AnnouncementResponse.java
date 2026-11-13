package com.resicomplex.communications.dto;

import java.time.LocalDateTime;

public record AnnouncementResponse(
        String id,
        String title,
        String content,
        String targetScope,
        String publishedBy,
        String status,
        LocalDateTime createdAt) {
}
