package com.app.ak1n.tatar.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "user_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    User user;

    String title;

    @Lob
    @Column(name = "text", columnDefinition = "TEXT")
    String text;

}
