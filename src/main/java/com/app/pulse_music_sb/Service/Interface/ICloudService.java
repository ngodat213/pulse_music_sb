package com.app.pulse_music_sb.Service.Interface;

import com.app.pulse_music_sb.Models.CloudStorage;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ICloudService {
    CloudStorage uploadFile(MultipartFile file, boolean isAudio);
    void deleteProductImage(String publicId);
}
