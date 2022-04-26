package ua.goit.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class SkillDto {
    private int id_skill;
    private String language;
    private String level_skill;


}
