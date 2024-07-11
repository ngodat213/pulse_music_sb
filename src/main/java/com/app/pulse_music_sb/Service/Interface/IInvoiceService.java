package com.app.pulse_music_sb.Service.Interface;

import com.app.pulse_music_sb.Models.AvailablePlan;
import com.app.pulse_music_sb.Models.Invoice;
import com.app.pulse_music_sb.Request.DTO.PaginationDTO;
import com.app.pulse_music_sb.Request.Request.RequestCreateAlbum;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface IInvoiceService {
    Page<Invoice> findAllBy(PaginationDTO paginationDTO);
    List<Invoice> findAll();
    Optional<Invoice> findById(String id);
    Invoice save(Invoice invoice);
    Invoice update(String id, Invoice invoice);
    void deleteById(String id);
}
