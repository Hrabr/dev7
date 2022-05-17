package ua.goit.dto;

import lombok.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SkillDto {
    private int id_skill;
    private String language;
    private String level_skill;
    private List<DeveloperDto> developers;

}
