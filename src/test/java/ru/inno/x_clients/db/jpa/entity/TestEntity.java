package ru.inno.x_clients.db.jpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "test", schema = "public", catalog = "x_clients_xxet")
public class TestEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;
}
