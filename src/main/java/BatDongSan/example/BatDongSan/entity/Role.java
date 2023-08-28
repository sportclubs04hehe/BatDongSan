package BatDongSan.example.BatDongSan.entity;

public enum Role {
    USER,         // Người dùng thông thường có quyền tiêu chuẩn
    ADMIN,        // Người dùng quản trị có quyền rộng
    MODERATOR     // Người dùng có thể kiểm duyệt nội dung nhưng không có quyền rộng rãi như QUẢN TRỊ VIÊN
}
