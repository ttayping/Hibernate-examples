package org.hibernate.practice.MODEL;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
//hibernate
@Table(name = "Employee")
@Entity
//lombok
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Employer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "experience")
    private int experience;
    @Column(name = "salary")
    private double salary;

    public Employer(String name, String surname, int experience, double salary) {
        this.name = name;
        this.surname = surname;
        this.experience = experience;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", experience=" + experience +
                ", salary=" + salary +
                '}';
    }
}
