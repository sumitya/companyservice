package com.example.companyservice.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyServiceImpl companyService;

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = companyService.getAllCompanies();
        if(companies != null) return ResponseEntity.ok(companies);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    @GetMapping("{id}")
    public ResponseEntity<Company> getCompany(@PathVariable Long id) {
        Company company = companyService.getCompanyById(id);
        if(company != null) return new ResponseEntity<>(company, HttpStatus.OK);
        return new ResponseEntity<>(new Company(1L,"TestCompany","test job"), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody Company company) {
        companyService.createCompany(company);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
    @PutMapping("{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody Company company) {
        boolean updated = companyService.updateCompany(id,company);
        if(updated) return new ResponseEntity<>(null, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
        boolean deleted = companyService.deleteCompanyById(id);
        if(deleted) return new ResponseEntity<>("Successfully deleted",HttpStatus.OK);
        else return new ResponseEntity<>("Not found",HttpStatus.NOT_FOUND);
    }

}
