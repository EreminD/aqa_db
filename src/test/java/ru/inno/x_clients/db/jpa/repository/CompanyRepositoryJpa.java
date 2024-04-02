package ru.inno.x_clients.db.jpa.repository;

import jakarta.persistence.EntityManager;
import ru.inno.x_clients.db.jpa.entity.CompanyEntity;

public class CompanyRepositoryJpa {
    private EntityManager entityManager;

    public CompanyEntity getById(int id){
        return entityManager.find(CompanyEntity.class, id);
    }

    public static void main(String[] args) {
        CompanyRepositoryJpa repo = new CompanyRepositoryJpa();

        repo.getById(1);
    }
}


