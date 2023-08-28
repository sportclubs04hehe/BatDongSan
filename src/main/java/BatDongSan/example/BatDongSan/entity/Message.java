package BatDongSan.example.BatDongSan.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "messages")
@Getter
@Setter
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "propertyID", referencedColumnName = "propertyID")
    private Property property; // Bối cảnh của cuộc trò chuyện

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "senderID", referencedColumnName = "userID")
    private User sender; // Các bên liên lạc.

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiverID", referencedColumnName = "userID")
    private User receiver; // Các bên liên lạc.

    private String subject; // Tóm tắt về nội dung tin nhắn
    private String messageBody; // Nội dung giao tiếp

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSent; //  Khi tin nhắn được gửi

    public Message(Property property, User sender, User receiver, String subject, String messageBody, Date dateSent) {
        this.property = property;
        this.sender = sender;
        this.receiver = receiver;
        this.subject = subject;
        this.messageBody = messageBody;
        this.dateSent = dateSent;
    }
}
