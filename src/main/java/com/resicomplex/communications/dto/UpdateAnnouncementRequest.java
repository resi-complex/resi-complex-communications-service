package com.resicomplex.communications.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateAnnouncementRequest(
        @NotBlank @Size(max = 200) String title,
        @NotBlank String content,
        @NotBlank @Pattern(regexp = "ALL|ONLY_RESIDENTIAL|ONLY_COMMERCIAL") String targetScope) {
}
