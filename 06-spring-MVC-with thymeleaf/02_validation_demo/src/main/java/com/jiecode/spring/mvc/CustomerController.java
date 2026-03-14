package com.jiecode.spring.mvc;

import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {

//add supprot for the InitBinder and string trimmerEditor
    //add an initbender to conver trim inout strings
    //remove laeading trailling whitespace
    //resolve issure for our validation
    @InitBinder
    public void initBinder(WebDataBinder binder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class,stringTrimmerEditor);

    }






    @GetMapping("/")
    public String index(Model model) {
        Customer thecustomer = new Customer();
        model.addAttribute("customer", thecustomer);
        return "customer-form";
    }

    @PostMapping("/processForm")
    public String processForm(@Valid @ModelAttribute("customer") Customer thecustomer,
                              BindingResult theBindingResult) {
        System.out.println("LastName: |" + thecustomer.getLastName()+"|");
        if (theBindingResult.hasErrors()) {
            return "customer-form";  // 如果有错误，返回表单页面
        } else {
            return "customer-confirmation";  // 否则，显示确认页面
        }
    }
}
