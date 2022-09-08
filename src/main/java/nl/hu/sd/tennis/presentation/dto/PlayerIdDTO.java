package nl.hu.sd.tennis.presentation.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class PlayerIdDTO {
    @Positive
    @NotBlank
    public long playerId;
}
