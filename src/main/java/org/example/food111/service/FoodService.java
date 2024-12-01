package org.example.food111.service;

import org.example.food111.mapper.FoodMapper;
import org.example.food111.entry.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class FoodService {

    @Autowired
    private FoodMapper foodMapper;

    @Value("${file.upload-dir}")  // 读取配置文件中的文件保存路径
    private String uploadDir;

    public void addFood(Food food) {
        // 生成唯一的文件名，避免文件名冲突
        MultipartFile pics = (MultipartFile)food.getPics();
        String originalFilename = pics.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString() + fileExtension;

        String projectDir = "D:\\develop\\web\\nginx-1.24.0\\nginx-1.24.0\\html";
        String uploadDir = projectDir + File.separator + "uploads" + File.separator + "food_images";
        // 创建目标文件夹（如果文件夹不存在，则创建它）
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();  // 如果文件夹不存在，则创建它
        }
        // 目标文件路径
        File uploadFile = new File(uploadDir + File.separator + newFileName);
        try {
            pics.transferTo(uploadFile);  // 将文件保存到硬盘
            System.out.println("文件保存成功!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 保存文件路径到数据库
        String filePath = uploadFile.getAbsolutePath();;
        food.setPics(filePath);
        foodMapper.addFood(food);
    }


    public List<Food> getFoods(Integer id) {
        List<Food> list = foodMapper.getFoods(id);
        for(Food food : list) {
            String baseDirectory = "D:\\develop\\web\\nginx-1.24.0\\nginx-1.24.0\\html\\uploads\\food_images\\";
            String a = (String) food.getPics();
            // 判断文件路径是否包含上传目录的根路径
            if (a.startsWith(baseDirectory)) {
                // 替换本地路径部分，生成访问URL
                String relativePath = a.replace(baseDirectory, "");
                food.setPics("/uploads/food_images/" + relativePath);
            }
        }
        return list;
    }

}
