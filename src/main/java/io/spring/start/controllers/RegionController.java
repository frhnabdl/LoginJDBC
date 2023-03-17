package io.spring.start.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.spring.start.models.Region;
import io.spring.start.services.RegionService;

//REGION 5
//class ini sebagai run / menjalankan perintahnya, dengan memanggil class region service
@Controller
@RequestMapping("region")
public class RegionController {
    private RegionService regionService; //memanggil class ini untuk menjalankan peritahnya
    
    @Autowired
    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping
    public String index(Model model){ //untuk get all / select * / read
        model.addAttribute("regions", regionService.Get());
        return "region/index";
    }

    //form -> GET & POST
    //form -> untuk insert / create
    //form/{id} -> untuk get by id / select where / read
    //@PathVariable(required = false) -> method ini tidak wajib membutuhkan parameter variabel
    @GetMapping(value = {"form", "form/{id}"})
    public String form(Model model, @PathVariable(required = false) Integer id){
        if(id != null){
            model.addAttribute("region", regionService.Get(id)); //untuk get by id / select where / read, habis itu masuk ke save
        }
        else{
            model.addAttribute("region", new Region()); //untuk insert / create, habis itu masuk ke save
        }        
        return "region/form";
    }

    @PostMapping("save")
    public String save(Region region){ //untuk INSERT & UPDATE ke database
        Boolean result = regionService.Save(region);
        if(result){
            return "redirect:/region";
        }
        return "region/form";
    }

    @PostMapping("delete/{id}")
    public String delete(@PathVariable(required = false) Integer id){
        regionService.Delete(id);
        return"redirect:/region";
    }
}
