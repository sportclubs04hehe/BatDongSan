package BatDongSan.example.BatDongSan.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

import static org.springframework.http.HttpHeaders.*;

@Configuration
@EnableWebMvc
public class WebConfig {
    private static final  Long MAX_AGE = 3600L;
    private static final int CORS_FILTER_ORDER = -102;

    @Bean
    public FilterRegistrationBean corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(); //nguồn cấu hình dựa trên URL để cấu hình CORS
        CorsConfiguration configuration = new CorsConfiguration(); //: Đối tượng cấu hình CORS
        configuration.setAllowCredentials(true); //Cho phép chia sẻ dữ liệu xác thực với tài nguyên
        configuration.addAllowedOrigin("http://localhost:4200"); //Thêm một nguồn gốc cho CORS được phép.
        configuration.setAllowedHeaders(Arrays.asList( //  Đặt danh sách các tiêu đề HTTP được phép trong yêu cầu CORS.
                AUTHORIZATION,
                CONTENT_TYPE,
                ACCEPT
        ));
        configuration.setAllowedMethods(Arrays.asList( // Đặt danh sách các phương thức HTTP được phép.
                HttpMethod.GET.name(),
                HttpMethod.POST.name(),
                HttpMethod.PUT.name(),
                HttpMethod.DELETE.name()
        ));
        configuration.setMaxAge(MAX_AGE); // Đặt thời gian tối đa mà cấu hình CORS được lưu trữ tại máy khách.
        source.registerCorsConfiguration("/**",configuration); // Đăng ký cấu hình CORS cho tất cả các URL.
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source)); //  Tạo một bộ lọc CORS với nguồn cấu hình đã đăng ký.
        bean.setOrder(CORS_FILTER_ORDER); //  Đặt thứ tự ưu tiên cho bộ lọc CORS trong chuỗi bộ lọc.
        return bean;
    }
}
