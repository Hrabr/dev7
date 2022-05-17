package ua.goit.dto;

import lombok.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {
    private int id_companies;
    private String name_companies;
    private String country_companies;
    private List<ProjectDto> projectsDto;


}
