package com.example.demo.service;


import com.example.demo.entity.PaymentType;
import com.example.demo.entity.ProductType;
import com.example.demo.entity.Selling;
import com.example.demo.entity.UnitType;
import com.example.demo.repository.PaymentTypeRepository;
import com.example.demo.repository.ProductTypeRepository;
import com.example.demo.repository.SellingRepository;
import com.example.demo.repository.UnitTypeRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SellingService {

    private final SellingRepository sellingRepository;

    private final ProductTypeRepository productTypeRepository;

    private final PaymentTypeRepository paymentTypeRepository;

    private final UnitTypeRepository unitTypeRepository;

    private final ReportService reportService;

    public List<Selling> getList(){
        return sellingRepository.findAll();
    }

    public String save(Selling selling){

        Optional<PaymentType> paymentType = paymentTypeRepository.findById(Long.valueOf(selling.getPaymentTypeId()));
        paymentType.ifPresent(selling::setPaymentType);

        Optional<ProductType> productType = productTypeRepository.findById(Long.valueOf(selling.getProductTypeId()));
        productType.ifPresent(selling::setProductType);

        Optional<UnitType> unitType = unitTypeRepository.findById(Long.valueOf(selling.getUnitTypeId()));
        unitType.ifPresent(selling::setUnitType);

        Selling sellingNew = sellingRepository.save(selling);
        if (sellingNew != null){
            return "Saved Successfully";
        }else {
            return "Error ocured";
        }
    }

    public double getSumValue(){
        return sellingRepository.sumValues();
    }

    public int getTotalNumber(){
        return sellingRepository.countById();
    }

    public void excelCollector(HttpServletResponse response, Date beginDate, Date endDate) throws IOException {
        String headerKey = "Content-Disposition";
        String fileName = "xisobot.xlsx";
        response.setContentType("application/octet-stream");
        response.setHeader(headerKey, "attachment; filename=" + java.net.URLEncoder.encode(fileName.replaceAll(" ", "_").toLowerCase(), StandardCharsets.UTF_8));

        XSSFWorkbook workbook = new XSSFWorkbook();

        List<Selling> sellings = sellingRepository.findAll();

        System.out.println("Downloading .... ");
        reportService.createRows(workbook,sellings);
        System.out.println("Workbook closed !!");
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        response.getOutputStream().flush();
        response.getOutputStream().close();
        outputStream.flush();

    }

}
