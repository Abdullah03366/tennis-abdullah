package nl.hu.sd.tennis.presentation.dto;

import javax.validation.constraints.NotBlank;

public class PlayerDTO {
    @NotBlank
    public String name;
}
