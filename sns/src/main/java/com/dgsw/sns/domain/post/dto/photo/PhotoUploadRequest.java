package com.dgsw.sns.domain.post.dto.photo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhotoUploadRequest {
    private String url;
    private String fileName;
}
