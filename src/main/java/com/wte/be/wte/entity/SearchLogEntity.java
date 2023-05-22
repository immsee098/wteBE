package com.wte.be.wte.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="search_log")
public class SearchLogEntity {
    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int log_idx;

    @Column(nullable = false)
    private String search_content;

    @Column(nullable = true)
    private String result;
}
