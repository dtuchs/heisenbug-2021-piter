package ru.heisenbug.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SayResponse {
    private String name;
}
