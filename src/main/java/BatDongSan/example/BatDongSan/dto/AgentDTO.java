package BatDongSan.example.BatDongSan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgentDTO {
    private Long agentID;
    private UserDto user;
    private String bio;
    private String agencyName;
    private Integer yearsExperience;
}
