package com.israt.carrentalproject.Controller;


import com.israt.carrentalproject.Entity.Agency;
import com.israt.carrentalproject.Entity.Car;
import com.israt.carrentalproject.Entity.Role;
import com.israt.carrentalproject.Jasper.MediaUtils;
import com.israt.carrentalproject.Jasper.TaskService;
import com.israt.carrentalproject.Repo.AgencyRepo;
import com.israt.carrentalproject.Repo.BookingRepo;
import com.israt.carrentalproject.Repo.CarRepo;
import com.israt.carrentalproject.Repo.CategoryRepo;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping(value = "/car/")
public class CarController {

    @Autowired
    private CarRepo carRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private AgencyRepo agencyRepo;

    @Autowired
    private ImageOptimizer imageOptimizer;

    @Autowired
    private TaskService taskService;

    @Autowired
    ServletContext context;

    private static String UPLOADED_FOLDER = "src/main/resources/static/ourcars/";

//    @Autowired
//    private BookingRepo bookingRepo;

    @GetMapping(value = "add")
    public String viewAdd(Model model) {
        model.addAttribute("car", new Car());
        model.addAttribute("categorylist", categoryRepo.findAll());
        model.addAttribute("agencylist", agencyRepo.findAll());
        return "cars/add";
    }


    @PostMapping(value = "add")
    public String add(@Valid Car car, BindingResult result, Model model,@RequestParam("file") MultipartFile file) {
        if (result.hasErrors()) {
            model.addAttribute("rejectMsg", "Somthing is wrong");
            model.addAttribute("categorylist", categoryRepo.findAll());
            model.addAttribute("agencylist", agencyRepo.findAll());
            return "cars/add";
        } else {
            try {
                //////////////////////For Image Upload start /////////////////////
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());

                Files.write(path, bytes);
                car.setFileName("new-" + file.getOriginalFilename());
                car.setFileSize(file.getSize());
                // user.setFile(file.getBytes());
                car.setFilePath("/ourcars/" + "new-" + file.getOriginalFilename());
                car.setFileExtension(file.getContentType());
                //////////////////////For Image Upload end/////////////////////
            this.carRepo.save(car);
            model.addAttribute("car", new Car());
            model.addAttribute("successMsg", "Successfully Saved!");

            model.addAttribute("categorylist", categoryRepo.findAll());

            model.addAttribute("agencylist", agencyRepo.findAll());
                imageOptimizer.optimizeImage(UPLOADED_FOLDER, file, 1.0f, 211, 150);
//                imageOptimizer.optimizeImage(UPLOADED_FOLDER, file, 0.3f, 100, 100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return "cars/add";
    }

    @GetMapping(value = "edit/{id}")
    public String viewEdit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("car", carRepo.getOne(id));

        model.addAttribute("categorylist", categoryRepo.findAll());

        model.addAttribute("agencylist", agencyRepo.findAll());
        return "cars/edit";
    }

    @PostMapping(value = "edit/{id}")
    public String edit(@Valid Car car, BindingResult result, Model model, @PathVariable("id") Long id,@RequestParam("file") MultipartFile file) {
        if (result.hasErrors()) {
            model.addAttribute("rejectMsg","Somthing is wrong");
            model.addAttribute("categorylist", categoryRepo.findAll());
            model.addAttribute("agencylist", agencyRepo.findAll());
            return "cars/edit";
//        }
//        Optional<Car> car1 = this.carRepo.findByCarModel(car.getCarModel());
//        if (car1.get().getId() != id) {
//            model.addAttribute("rejectMsg", "Already Have This Entry");
//
//            model.addAttribute("categorylist", categoryRepo.findAll());
//            model.addAttribute("agencylist", agencyRepo.findAll());
//            return "cars/edit";
        } else {
            try {
                //////////////////////For Image Upload start /////////////////////
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());

                Files.write(path, bytes);
                car.setFileName("new-" + file.getOriginalFilename());
                car.setFileSize(file.getSize());
                // user.setFile(file.getBytes());
                car.setFilePath("/ourcars/" + "new-" + file.getOriginalFilename());
                car.setFileExtension(file.getContentType());
                //////////////////////For Image Upload end/////////////////////
            car.setId(id);
            this.carRepo.save(car);
            model.addAttribute("car",new Car());
            model.addAttribute("successMsg", "Successfully Saved!");
            model.addAttribute("categorylist", categoryRepo.findAll());
            model.addAttribute("agencylist", agencyRepo.findAll());
                imageOptimizer.optimizeImage(UPLOADED_FOLDER, file, 1.0f, 211, 150);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "redirect:/car/list";
    }


    @GetMapping(value = "del/{id}")
    public String del(@PathVariable("id") Long id) {
        if (id != null) {
            this.carRepo.deleteById(id);
        }
        return "redirect:/car/list";
    }

    @GetMapping(value = "list")
    public String list(Model model) {
        model.addAttribute("list", this.carRepo.findAll());
        return "cars/list";
    }

    @GetMapping(value = "cars")
    public String cars(Model model) {
        model.addAttribute("cars", this.carRepo.findAll());
        return "cars/carsphoto";
    }

    ////////////////////////////JASPER/////////////////////////////////

    @RequestMapping(value = "report", method = RequestMethod.GET)
    public void report(HttpServletResponse response) throws Exception {
        response.setContentType("text/html");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(taskService.carreport());
        InputStream inputStream = this.getClass().getResourceAsStream("/report.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
        HtmlExporter exporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));
        exporter.exportReport();
    }

    ////////////////pdf//////////////////////

    //    @RequestMapping(value = "/pdf", method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_PDF_VALUE)
    public void reportPdf() throws Exception {
        String source = "F:\\my_git\\my_spring\\daily_work\\carrentalproject\\src\\main\\resources\\report.jrxml";
        try {
            JasperCompileManager.compileReportToFile(source);
        } catch (JRException e) {
            e.printStackTrace();
        }
        String sourceFileName = "F:\\my_git\\my_spring\\daily_work\\carrentalproject\\src\\main\\resources\\report1.jasper";
        String printFileName = null;
        String destFileName = "F:\\my_git\\my_spring\\daily_work\\carrentalproject\\src\\main\\resources\\report.pdf";
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(taskService.carreport());
        Map parameters = new HashMap();
        try {
            printFileName = JasperFillManager.fillReportToFile(sourceFileName,
                    parameters, dataSource);
            if (printFileName != null) {
                JasperExportManager.exportReportToPdfFile(printFileName,
                        destFileName);
            }
        } catch (JRException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("pdf")
    public ResponseEntity<InputStreamResource> downloadFile1() throws IOException {
        try {
            reportPdf();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String fileName="F:\\\\my_git\\\\my_spring\\\\daily_work\\\\carrentalproject\\\\src\\\\main\\\\resources\\\\report.pdf";
        MediaType mediaType = MediaUtils.getMediaTypeForFileName(this.context, fileName);

        File file = new File(fileName);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                // Content-Type
                .contentType(mediaType)
                // Contet-Length
                .contentLength(file.length()) //
                .body(resource);
    }

}
