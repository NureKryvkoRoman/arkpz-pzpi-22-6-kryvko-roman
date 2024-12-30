package ua.nure.kryvko.roman.Atark.greenhouse;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import ua.nure.kryvko.roman.Atark.notification.Notification;
import ua.nure.kryvko.roman.Atark.sensor.Sensor;
import ua.nure.kryvko.roman.Atark.user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "greenhouse")
public class Greenhouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    User user;

    @CreatedDate
    LocalDateTime createdAt;

    @NotNull
    String name;

    @NotNull
    Float latitude;

    @NotNull
    Float longitude;

    @OneToMany(mappedBy = "greenhouse")
    List<Sensor> sensors = new ArrayList<>();

    @OneToMany(mappedBy = "greenhouse")
    List<Notification> notifications = new ArrayList<>();

    public Greenhouse(User user, LocalDateTime createdAt, String name, Float latitude, Float longitude) {
        this.user = user;
        this.createdAt = createdAt;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }
}
