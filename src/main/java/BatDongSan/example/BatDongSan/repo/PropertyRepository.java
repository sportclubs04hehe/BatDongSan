package BatDongSan.example.BatDongSan.repo;

import BatDongSan.example.BatDongSan.entity.Property;
import BatDongSan.example.BatDongSan.entity.PropertyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    Optional<Property> findPropertiesByPropertyID(Long id); // tìm kiếm id người dùng
    List<Property> findPropertiesByPropertyType(PropertyType type); // Tìm kiếm dựa trên loại bất động sản
    List<Property> findByPriceLessThan (BigDecimal priceMax); // tìm dự án có giá dưới một ngưỡng nhất định ( ví dự dưới ngưỡng 2 tỉ)
    List<Property> findPropertiesByPriceGreaterThan(BigDecimal minPrice); // ngược lại bên trên (ví dụ tìm dự án trên 2 tỉ)
    List<Property> findPropertiesByPriceBetween(BigDecimal maxPrice, BigDecimal minPrice); // tìm trong khoảng giá 2 đến 4 tỉ

//    List<Property> findPropertiesByAreBetween(Double minArea, Double maxArea);
    /** Tìm kiếm dựa trên địa chỉ (tỉnh, huyện, xã)*/
    List<Property> findPropertiesByAddressContaining(String keyword);
    List<Property> findPropertiesProvinceAndDistrictAndWard(String province, String district, String ward);
/**    Tìm kiếm dự án của một người dùng cụ thể */
    List<Property> findPropertiesByOwnerEmail(String owenEmail);
}
