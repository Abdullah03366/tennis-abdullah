package nl.hu.sd.tennis.presentation.dto;

import javax.validation.constraints.*;

public class SetDTO {
    @NotBlank
    @Positive
    public long player1Id;
    @NotBlank
    @Positive
    public long player2Id;
}
