package com.app.ak1n.tatar.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Getter
@Setter
@NoArgsConstructor
@Table(name = "p_like")
@Entity
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    @ToString.Exclude
    User user;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "post_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    @ToString.Exclude
    Post post;

}
