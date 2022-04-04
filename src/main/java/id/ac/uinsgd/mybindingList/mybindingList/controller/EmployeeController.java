package id.ac.uinsgd.mybindingList.mybindingList.controller;

import id.ac.uinsgd.mybindingList.mybindingList.employee.Employee;
import id.ac.uinsgd.mybindingList.mybindingList.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@AllArgsConstructor
@RequestMapping(value = "/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public String showEmployeeForm (Model model) {
        model.addAttribute("employee", new Employee());

        return "employees/createEmployeeForm";
    }

    @PostMapping (consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String saveEmployee(@ModelAttribute Employee employee) {
        employeeService.save(employee);

        return "employee/success";
    }

    @PostMapping (path = "/requestpart", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> saveEmployee(@RequestPart Employee employee, @RequestPart MultipartFile document) {
        employee.setDocument(document);
        employeeService.save(employee);

        return ResponseEntity.ok().build();
    }

    @PostMapping (path = "/requestparam", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> saveEmployee(@RequestParam String name, @RequestPart MultipartFile document) {
        Employee employee = new Employee(name, document);
        employeeService.save(employee);

        return ResponseEntity.ok().build();
    }
}
