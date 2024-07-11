package com.app.pulse_music_sb.Service;

import com.app.pulse_music_sb.Models.CloudStorage;
import com.app.pulse_music_sb.Repository.ImageStorageRepository;
import com.app.pulse_music_sb.Service.Interface.ICloudService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;


@Service
public class CloudService implements ICloudService {

    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private ImageStorageRepository pImageRepository;

    @Override
    public CloudStorage uploadFile(MultipartFile file, boolean isAudio){
        Map uploadResult = uploadFileToCloudinary(file, isAudio);
        return  pImageRepository.save(
                new CloudStorage(
                        uploadResult.get("url").toString(),
                        uploadResult.get("asset_id").toString(),
                        uploadResult.get("public_id").toString())
        );
    }

    @Override
    public void deleteProductImage(String publicId) {
        try {
            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    private Map uploadFileToCloudinary(MultipartFile file, boolean isAudio){
        if (!file.isEmpty() && (isAudio ? isAudio(file) : isImage(file))) {
            try {
                byte[] fileBytes = file.getBytes();
                System.out.println("File size: " + fileBytes.length + " bytes");
                String resourceType = isAudio ? "video" : "image";
                return cloudinary.uploader().upload(fileBytes, ObjectUtils.asMap("resource_type", resourceType));
            } catch (IOException ex) {
                throw new RuntimeException("Can't upload file, try again!");
            }
        } else {
            throw new RuntimeException("Unsupported file format");
        }
    }

    private boolean isImage(MultipartFile file) {
        return file.getContentType() != null && file.getContentType().startsWith("image");
    }

    private boolean isAudio(MultipartFile file) {
        return file.getContentType() != null && file.getContentType().startsWith("audio");
    }


}