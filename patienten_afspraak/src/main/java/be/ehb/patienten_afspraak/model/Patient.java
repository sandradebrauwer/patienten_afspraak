package be.ehb.patienten_afspraak.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.*;

@Entity
public class Patient {

    @Id
    public long id;

    public String lastname;
    public String firstname;
    public LocalDate birthday;

    @OneToMany(mappedBy = "patient")
    private Set<Afspraak> afspraken = new HashSet<>();

    public Patient(Long id, String lastname, String firstname, Date birthday) {
    }

    public Patient(long id, String lastname, String firstname, LocalDate birthday) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.birthday = birthday;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }



    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", birthday=" + birthday +
                ", afspraken=" + afspraken +
                '}';
    }
}
