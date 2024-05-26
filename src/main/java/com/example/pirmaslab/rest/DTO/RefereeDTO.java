package com.example.pirmaslab.rest.DTO;

import com.example.pirmaslab.entities.Referee;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RefereeDTO {
    private String Name;
    private String Surname;
    private String Email;

    public static RefereeDTO toRefereeDTO(Referee referee) {
        RefereeDTO refereeDTO = new RefereeDTO();

        refereeDTO.setName(referee.getName());
        refereeDTO.setSurname(referee.getSurname());
        refereeDTO.setEmail(referee.getEmail());

        return refereeDTO;
    }

    public static Referee toReferee(RefereeDTO refereeDTO) {
        Referee referee = new Referee();

        referee.setName(refereeDTO.getName());
        referee.setSurname(refereeDTO.getSurname());
        referee.setEmail(refereeDTO.getEmail());

        return referee;
    }
}
