package com.academy.beerhouse.craftbeer.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
@Table("craftbeers")
public class Beer {
    @Id
    private UUID id;
    @NotNull
    @NotEmpty(message = "The name of this beer cannot be empty")
    private String name;
}
