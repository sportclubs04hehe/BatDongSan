package BatDongSan.example.BatDongSan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {
    private Long messageID;
    private PropertyDTO property;
    private UserDto sender;
    private UserDto receiver;
    private String subject;
    private String messageBody;
    private Date dateSent;
}
