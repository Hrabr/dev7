package ua.goit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {
    private int id_projects;
    private String name_projects;
    private BigDecimal cost_project;
    private LocalDate start_project;
    private List<DeveloperDto> developers;
}
