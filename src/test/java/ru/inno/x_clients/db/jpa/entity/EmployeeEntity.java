package ru.inno.x_clients.db.jpa.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "employee", schema = "public", catalog = "x_clients_xxet")
public class EmployeeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "is_active", nullable = false)
    private boolean isActive;
    @Basic
    @Column(name = "create_timestamp")
    private Timestamp createTimestamp;
    @Basic
    @Column(name = "change_timestamp")
    private Timestamp changeTimestamp;
    @Basic
    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;
    @Basic
    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;
    @Basic
    @Column(name = "middle_name", nullable = true, length = 20)
    private String middleName;
    @Basic
    @Column(name = "phone", nullable = false, length = 15)
    private String phone;
    @Basic
    @Column(name = "email", nullable = true, length = 256)
    private String email;
    @Basic
    @Column(name = "birthdate", nullable = true)
    private Date birthdate;
    @Basic
    @Column(name = "avatar_url", nullable = true, length = 1024)
    private String avatarUrl;

    @ManyToOne
    @JoinColumn(name="company_id")
    private CompanyEntity company;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Object getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Timestamp createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public Object getChangeTimestamp() {
        return changeTimestamp;
    }

    public void setChangeTimestamp(Timestamp changeTimestamp) {
        this.changeTimestamp = changeTimestamp;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }


    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeEntity employee)) return false;
        return getId() == employee.getId() && isActive() == employee.isActive() && Objects.equals(getCreateTimestamp(), employee.getCreateTimestamp()) && Objects.equals(getChangeTimestamp(), employee.getChangeTimestamp()) && Objects.equals(getFirstName(), employee.getFirstName()) && Objects.equals(getLastName(), employee.getLastName()) && Objects.equals(getMiddleName(), employee.getMiddleName()) && Objects.equals(getPhone(), employee.getPhone()) && Objects.equals(getEmail(), employee.getEmail()) && Objects.equals(getBirthdate(), employee.getBirthdate()) && Objects.equals(getAvatarUrl(), employee.getAvatarUrl()) && Objects.equals(getCompany(), employee.getCompany());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), isActive(), getCreateTimestamp(), getChangeTimestamp(), getFirstName(), getLastName(), getMiddleName(), getPhone(), getEmail(), getBirthdate(), getAvatarUrl(), getCompany());
    }

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "id=" + id +
                ", isActive=" + isActive +
                ", createTimestamp=" + createTimestamp +
                ", changeTimestamp=" + changeTimestamp +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", birthdate=" + birthdate +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", company=" + company.getId() +
                '}';
    }
}
