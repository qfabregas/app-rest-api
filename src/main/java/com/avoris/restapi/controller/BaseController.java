package com.avoris.restapi.controller;

import com.avoris.restapi.exception.ResourceNotFoundException;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

public class BaseController {

    protected static final Logger log = LoggerFactory.getLogger(BaseController.class);

    // Method to create the pdf file using the employee list datasource.
    public void createReport (HttpServletResponse response,
                               final List<?> parameter,
                               Map<String, Object> parameters,
                               String filereport,
                               String filename,
                               String filetype) throws ResourceNotFoundException {
        try {

            // Fetching the .jrxml file from the resources folder.
            final InputStream stream = this.getClass().getResourceAsStream("/" + filereport);

            // Compile the Jasper report from .jrxml to .japser
            final JasperReport jasperReport = JasperCompileManager.compileReport(stream);

            // Fetching the employees from the data source.
            final JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(parameter);

            final JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);


            if (filetype.equals("pdf")) {
                response.setContentType("application/pdf");
                response.addHeader("Content-disposition", "attachment; filename=" + filename + "." + filetype);
                OutputStream outputStream = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
                outputStream.flush();
                outputStream.close();
            }
            else {
                log.error("Type no controlado. [BaseController.createReport()] : " + filetype);
            }

        } catch (Exception e) {
            throw new ResourceNotFoundException("Error in generating pdf : " + e);
        }
    }
}
