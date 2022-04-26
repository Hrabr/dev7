package ua.goit.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class CompaniesDto {
  private int id_companies;
  private String name_companies;
  private String country_companies;
  private List<ProjectsDto> projectsDto;


}
