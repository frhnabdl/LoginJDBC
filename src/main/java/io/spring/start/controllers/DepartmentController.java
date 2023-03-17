package io.spring.start.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.spring.start.models.Department;
import io.spring.start.services.DepartmentService;
import io.spring.start.services.RegionService;

@Controller
@RequestMapping("department")
public class DepartmentController{
    private DepartmentService departmentService;
    private RegionService regionService;

    @Autowired
    public DepartmentController(DepartmentService departmentService, RegionService regionService){
        this.departmentService = departmentService;
        this.regionService = regionService;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("departments", departmentService.GetAllByRegion(8));        
        return "department/index";
    }

    @GetMapping(value = {"form", "form/{id}"})
    public String form(Model model, @PathVariable(required = false) Integer id){
        if(id != null){
            model.addAttribute("department", departmentService.Get(id)); 
            model.addAttribute("regions", regionService.Get());
        }
        else{
            model.addAttribute("department", new Department());
            model.addAttribute("regions", regionService.Get());
        }        
        return "department/form";
    }

    @PostMapping("save")
    public String save(Department department){
        Boolean result = departmentService.Save(department);
        if(result){
            return "redirect:/department";
        }
        return "department/form";
    }

    @PostMapping("delete/{id}")
    public String delete(@PathVariable(required = false) Integer id){
        departmentService.Delete(id);
        return"redirect:/department";
    }

}