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
@Table(name = "customers")
public class CustomerDao {
    @Id
    @GeneratedValue(generator = "customers_id_customers_seq")
    @Column(name = "id")
    private int id;
    @Column(name = "name_customers")
    private String name_customers;
    @Column(name = "country_customers")
    private String country_customers;
    @OneToMany(cascade=CascadeType.MERGE)
    @JoinColumn(name="customer_id")
    private List<ProjectDao> projectsDao;
}
