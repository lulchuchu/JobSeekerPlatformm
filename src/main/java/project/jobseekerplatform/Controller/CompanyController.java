package project.jobseekerplatform.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.jobseekerplatform.Model.entities.Company;
import project.jobseekerplatform.Services.CompanyService;

import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/api/company")
    public List<Company> getAllCompaniesByNameContain(@RequestParam("containing") String string) {
        return companyService.findCompanyByName(string);
    }


    @PostMapping("/api/company/add")
    public void addCompany(Company company) {
        companyService.addCompany(company);
    }

}
