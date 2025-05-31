package com.example.studentmanagementsystem.firebase;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FCMRequest {
    private String token;
    private String title;
    private String body;
}
