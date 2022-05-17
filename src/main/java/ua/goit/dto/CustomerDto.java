package ua.goit.dto;

import lombok.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private int id_customers;
    private String name_customers;
    private String country_customers;
    private List<ProjectDto> projectsDto;
}
