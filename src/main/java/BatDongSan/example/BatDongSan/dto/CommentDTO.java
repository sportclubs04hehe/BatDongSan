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
public class CommentDTO {
    private Long commentID;
    private PropertyDTO property;
    private UserDto user;
    private String text;
    private Date datePosted;
}
