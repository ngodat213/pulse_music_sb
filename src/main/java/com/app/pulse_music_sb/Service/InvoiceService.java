package com.app.pulse_music_sb.Service;

import com.app.pulse_music_sb.Models.Invoice;
import com.app.pulse_music_sb.Repository.InvoiceRepository;
import com.app.pulse_music_sb.Request.DTO.PaginationDTO;
import com.app.pulse_music_sb.Service.Interface.IInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService implements IInvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private PaginationService paginationService;

    @Override
    public Page<Invoice> findAllBy(PaginationDTO paginationDTO) {
        Pageable pageable = paginationService.getPageable(paginationDTO);
        return invoiceRepository.findAll(pageable);
    }

    @Override
    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }

    @Override
    public Optional<Invoice> findById(String id) {
        return invoiceRepository.findById(id);
    }

    @Override
    public Invoice save(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice update(String id, Invoice invoice) {
        return null;
    }

    @Override
    public void deleteById(String id) {
        Invoice invoice = invoiceRepository.findById(id).orElse(null);
        invoice.setDeleted(true);
        invoiceRepository.save(invoice);
    }
}
