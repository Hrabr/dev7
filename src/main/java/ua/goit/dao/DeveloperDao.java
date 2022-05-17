package ua.goit.dao;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "developers")
public class DeveloperDao {
    @Id
    @GeneratedValue(generator = "developers_id_developers_seq")
    @Column(name = "id_developers")
    private int id_developers;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;
    @Column(name = "gender")
    private String gender;
    @Column(name = "salary")
    private BigDecimal salary;
    @ManyToMany(
            cascade = {CascadeType.MERGE,CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinTable(
            name = "developers_skill",
            joinColumns = { @JoinColumn(name = "developer_id", updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "skill_id", updatable = false) }
    )
    private List<SkillDao> skillDao = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.MERGE,  CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinTable(
            name = "developers_projects",
            joinColumns = { @JoinColumn(name = "developer_id",updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "projects_id",updatable = false) }
    )
    private List<ProjectDao> projectsDao=new ArrayList<>();
}
