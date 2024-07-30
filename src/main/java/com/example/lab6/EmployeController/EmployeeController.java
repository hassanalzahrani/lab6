package com.example.lab6.EmployeController;

import com.example.lab6.Apirespons.ApiResponse;
import com.example.lab6.Employee.Employe;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@RestController
@RequestMapping("/api/v1/event")
public class EmployeeController {

    ArrayList<Employe> employes = new ArrayList();

    @GetMapping
    public ResponseEntity getEmployees() {
        return ResponseEntity.status(200).body(employes);

    }

    @PostMapping("add")
    public ResponseEntity addEmployee(@Valid @RequestBody Employe employee, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        employes.add(employee);
        return ResponseEntity.status(200).body(new ApiResponse("Employee added successfully"));
    }

    @PutMapping("update/{index}")
    public ResponseEntity updateEmployee(@PathVariable int index, @Valid @RequestBody Employe employee, Errors errors) {
        if (errors.hasErrors()) {

            String message = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(400).body(message);

        }
        employes.set(index, employee);
        return ResponseEntity.status(200).body(new ApiResponse("Employee updated successfully"));
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteEmployee(@PathVariable int index) {
        employes.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("Employee deleted successfully"));
    }

    @GetMapping("search/{position}")
    public ResponseEntity searchEmployee(@Valid @PathVariable String position) {
        for (Employe employee : employes) {
            if (employee.getPosition().equals(position)) {
                return ResponseEntity.status(200).body(employee);

            }
        }
        return ResponseEntity.status(200).body(new ApiResponse("Employee not found"));
    }

    @GetMapping("/get/{age}")
    public ResponseEntity getEmployeeAge(@PathVariable int age) {
        for (Employe employee : employes) {
            if (employee.getAge() == age) {
                return ResponseEntity.status(200).body(employee);
            }
        }

        return ResponseEntity.status(200).body(new ApiResponse("Employee not found"));
    }

    @PutMapping ("/apply-leave/{id}")
    public ResponseEntity applyForAnnualLeave(@PathVariable String id) {

        for (Employe employee : employes) {
            if (employee.getId().equals(id)) {
                if (employee.isOnLeave() == false) {

                    if (employee.getAnnualLeave() >= 1) {
                        employee.setAnnualLeave(employee.getAnnualLeave() - 1);
                        employee.setOnLeave(true);
                        return ResponseEntity.status(200).body(new ApiResponse("Employee has been applied"));
                    }else {return ResponseEntity.status(200).body(new ApiResponse("Employee has no annual leave balance"));}


                }else {
                    return ResponseEntity.status(200).body(new ApiResponse("Employee is on leave"));
                }


            }else {
                return ResponseEntity.status(404).body(new ApiResponse("Employee not found"));
            }


        }
        return ResponseEntity.status(400).body(new ApiResponse("Employee cant apply for annual leave"));
    }

    @GetMapping("/get/{index}")
    public ResponseEntity getEmployeeByAnnualLeave(@PathVariable int index) {
        for (Employe employee : employes) {
            if (employee.getAnnualLeave() == index) {
                return ResponseEntity.status(200).body(employee);
            }
        }
        return ResponseEntity.status(200).body(new ApiResponse("Employees has No Annual Leave"));
    }

    @PutMapping ("/prompte/{ids}/{ide}")
    public ResponseEntity prompteEmployee(@PathVariable String ids, @PathVariable String ide) {


        for (Employe employee : employes) {
            if (employee.getId().equals(ids)&&employee.getPosition().equalsIgnoreCase("supervisor")) {
               for (Employe employe2:employes){
                   if (employe2.getId().equalsIgnoreCase(employee.getId())&&employe2.getPosition().equalsIgnoreCase("coordinator")) {
                       if (employe2.isOnLeave() == false) {
                           if (employe2.getAge()>=30){
                               employe2.setPosition("supervisor");
                               return ResponseEntity.status(200).body(new ApiResponse("Employee has been prompted"));
                           }else {
                               return ResponseEntity.status(400).body(new ApiResponse("Employee is under 30 years old"));
                           }

                       }else {
                           return ResponseEntity.status(400).body(new ApiResponse("Employee is on leave"));
                       }
                   }else {
                       return ResponseEntity.status(400).body(new ApiResponse("coordinator not found"));

                   }
               }




            }else {
                return ResponseEntity.status(400).body(new ApiResponse("supervisor not found"));
            }



            }
        return ResponseEntity.status(200).body(new ApiResponse("Employee has not  been prompted"));
        }




    }







