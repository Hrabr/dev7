package ua.goit.dao;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "skill")
public class SkillDao {
    @Id
    @GeneratedValue(generator = "skill_id_skill_seq")
    private int id_skill;
    @Column(name = "language")
    private String language;
    @Column(name = "level_skill")
    private String level_skill;

    @ManyToMany(
            cascade = {CascadeType.MERGE,CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinTable(
            name = "developers_skill",
            joinColumns = { @JoinColumn(name = "skill_id") },
            inverseJoinColumns = { @JoinColumn(name = "developer_id") }
    )
    private List<DeveloperDao> developers ;
}
