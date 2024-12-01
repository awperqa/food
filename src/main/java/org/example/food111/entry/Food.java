package org.example.food111.entry;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class Food {
    private Integer tno;  // 菜品类型（假设是一个整型）
    private String pname; // 菜品名称
    private Double price; // 菜品成本价
    private Double salePrice; // 菜品销售价
    private String unit; // 菜品单位
    private Double weight; // 菜品重量
    private Object pics; // 菜品图片（使用 MultipartFile 来处理文件上传）
    private String mark; // 菜品描述
}
