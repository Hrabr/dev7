package ua.goit.dao;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "projects")
public class ProjectDao {
    @Id
    @GeneratedValue(generator = "projects_id_projects_seq")
    @Column(name = "id_projects")
    private int id_projects;
    @Column(name = "name_projects")
    private String name_projects;
    @Column(name = "cost_project")
    private BigDecimal cost_project;
    @Column(name = "start_project")
    private LocalDate start_project;

    @ManyToOne()
    @JoinColumn(name="company_id")
    private CompanyDao company;

    @ManyToOne()
    @JoinColumn(name="customer_id")
    private CustomerDao customer;

    @ManyToMany(
            cascade = {CascadeType.MERGE, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinTable(
            name = "developers_projects",
            joinColumns = { @JoinColumn(name = "projects_id") },
            inverseJoinColumns = { @JoinColumn(name = "developer_id") }
    )
    private List<DeveloperDao> developers;
}
