package com.example.hw27.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "title must not be empty")
    @Size(min = 3, message = "title must be more than 3 letters")
    @Column(columnDefinition = "varchar(30) not null")
    private String title;

    @NotEmpty(message = "body must not be empty")
    @Size(min = 3, message = "body must be more than 3 letters")
    @Column(columnDefinition = "varchar(30) not null")
    private String body;



    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private User user;
}
