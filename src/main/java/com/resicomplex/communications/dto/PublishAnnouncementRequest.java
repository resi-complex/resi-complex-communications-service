package com.resicomplex.communications.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record PublishAnnouncementRequest(
        @NotBlank @Size(max = 200) String title,
        @NotBlank String content,
        @NotBlank @Pattern(regexp = "ALL|ONLY_RESIDENTIAL|ONLY_COMMERCIAL") String targetScope,
        @NotBlank @Size(max = 36) String publishedBy) {
}
