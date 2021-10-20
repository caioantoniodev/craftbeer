package com.academy.beerhouse.craftbeer.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
@Table("beers")
@JsonNaming(SnakeCaseStrategy.class)
public class Beer {
    @Id
    private UUID id;

    @NotNull
    @NotBlank(message = "The name of this beer cannot be empty")
    private String name;

    @NotNull
    @NotBlank()
    private Integer alcoholContent;

    @NotNull
    @DecimalMin(value = "0.0")
    private BigDecimal price;
}
