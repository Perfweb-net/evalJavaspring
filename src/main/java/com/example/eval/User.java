package com.example.eval;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "points", nullable = false)
    private Integer points = 0;

    @Column(name = "last_question_id")
    private Integer lastQuestionId;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getLastQuestionId() {
        return lastQuestionId;
    }

    public void setLastQuestionId(Integer lastQuestionId) {
        this.lastQuestionId = lastQuestionId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", points=" + points +
                ", lastQuestionId=" + lastQuestionId +
                '}';
    }
}
