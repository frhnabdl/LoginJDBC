package io.spring.start.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.spring.start.dto.ChangePassword;
import io.spring.start.dto.ForgetPassword;
import io.spring.start.dto.Login;
import io.spring.start.models.Employee;
import io.spring.start.models.Role;
import io.spring.start.models.User;
import io.spring.start.repositories.ManagementRepository;
import io.spring.start.services.EmployeeService;
import io.spring.start.services.RoleService;
import io.spring.start.services.UserService;

// controller untuk login
@Controller
@RequestMapping("user")
public class UserManagementController {
  private AuthenticationManager authenticationManager;
  private EmployeeService employeeService;
  private UserService userService;
  private RoleService roleService;  
  private PasswordEncoder passwordEncoder;  
  private ManagementRepository managementRepository;

  @Autowired
  public UserManagementController(AuthenticationManager authenticationManager, EmployeeService employeeService, UserService userService, RoleService roleService, PasswordEncoder passwordEncoder, ManagementRepository managementRepository){
    this.authenticationManager = authenticationManager;
    this.employeeService = employeeService;
    this.userService = userService;
    this.roleService = roleService;    
    this.passwordEncoder = passwordEncoder;
    this.managementRepository = managementRepository;
  }

  // login
  @GetMapping("login")
  public String formLogin(Model model) {
    model.addAttribute("login", new Login()); 
    return "user-management/login";
  }

  // ini untuk berhasil /tidak login nya
  @PostMapping("authenticating")
  public String submitLogin(Login login){
    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);        
    return "user-management/dashboard";
  }

  // get model regis
  // getmapping ini untuk get/ambil data kosong setter getternya dari setiap model employee dan user
  @GetMapping("register")
    public String formRegister(Model model){                  
      model.addAttribute("employee", new Employee());      
      model.addAttribute("user", new User());
      model.addAttribute("roles", roleService.Get());      
      return "user-management/register";
    }

  // post register
  // register
  @PostMapping("submitRegister")
  public String submitRegister(Employee employee, User user){
    Boolean resultEmployee = employeeService.Save(employee); // insert into table database nya
    Role role = new Role();
    role.setId(2);

    if(resultEmployee){   
      user.setId(employee.getId());
      user.setPassword(passwordEncoder.encode(user.getPassword()));            
      user.setRole(role);
      userService.Save(user);
      return "redirect:/login";
    }        
    return "redirect:/register";
  }
  
  // change password
  @GetMapping("changePassword")
    public String changePassword(Model model){ //untuk get all / select * / read
        model.addAttribute("changePW", new ChangePassword());
        return "user-management/changePassword";
    }

  @PostMapping("submitChangePassword")
  public String submitChangePassword(ChangePassword changePW, Principal principal){ //untuk get all / select * / read
    // ambil email dari yg baru di input
    User email = managementRepository.Login(principal.getName());
    // cek old pw yg baru di input dengan yg sudah di simpan di database
    Boolean cekPW = passwordEncoder.matches(changePW.getOldPassword(), email.getPassword());

    if(cekPW){
      // email.getId() -> ngambil data dari email yg di dapat
      User dataUser = userService.Get(email.getId());
      dataUser.setPassword(passwordEncoder.encode(changePW.getNewPassword()));
      userService.Save(dataUser);
      return "user-management/dashboard";
    }
    return "redirect:/user/changePassword";
  }
  
  // forget password
  @GetMapping("forgetPassword")
    public String forgetPassword(Model model){ 
        model.addAttribute("forgetPW", new ForgetPassword());
        return "user-management/forgetPassword";
    }

  @PostMapping("submitForgetPassword")
  public String submitForgetPassword(ForgetPassword forgetPW){           
    // principal ini dari cookie ngambil dari sistem yg baru saja di input
    User email = managementRepository.Login(forgetPW.getEmail());    

    if(forgetPW.getEmail().equals(email.getEmployee().getEmail())){
      User dataUser = userService.Get(email.getId());
      dataUser.setPassword(passwordEncoder.encode(forgetPW.getNewPassword()));
      userService.Save(dataUser);
      return "redirect:/user/login";
    }
    return "user-management/forgetPassword";        
  }
  

}
