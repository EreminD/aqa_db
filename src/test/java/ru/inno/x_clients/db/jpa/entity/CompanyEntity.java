package ru.inno.x_clients.db.jpa.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "company", schema = "public", catalog = "x_clients_xxet")
public class CompanyEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String description;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    @OneToMany(mappedBy = "company")
    private List<EmployeeEntity> employees;

    public List<EmployeeEntity> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeEntity> employees) {
        this.employees = employees;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompanyEntity company)) return false;
        return getId() == company.getId() && isActive() == company.isActive() && Objects.equals(getName(), company.getName()) && Objects.equals(getDescription(), company.getDescription()) && Objects.equals(getEmployees(), company.getEmployees());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), isActive(), getEmployees());
    }

    @Override
    public String toString() {
        return "CompanyEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isActive=" + isActive +
                ", employees=" + employees.size() +
                '}';
    }
}
