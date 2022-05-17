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
@Table(name = "companies")
public class CompanyDao {
    @Id
    @GeneratedValue(generator = "companies_id_companies_seq")
    @Column(name = "id")
    private int id;
    @Column(name = "name_companies")
    private String name_companies;
    @Column(name = "country_companies")
    private String country_companies;
    @OneToMany(cascade=CascadeType.MERGE)
    @JoinColumn(name="company_id")
   private List<ProjectDao> projectsDao;
}
