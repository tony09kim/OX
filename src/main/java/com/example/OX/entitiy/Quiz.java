package com.example.OX.entitiy;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name="quiz")
@Data
@ToString
public class Quiz {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 20, nullable = false)
    private String text;
    private boolean o;
    private boolean x;
    @Column(name= "user" , length = 100)
    private String user;
}
