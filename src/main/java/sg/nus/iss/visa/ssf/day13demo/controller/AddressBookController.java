package sg.nus.iss.visa.ssf.day13demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import sg.nus.iss.visa.ssf.day13demo.model.Contact;
import sg.nus.iss.visa.ssf.day13demo.utility.Utility;

@Controller
@RequestMapping(path="/")
public class AddressBookController {
    
    //request method to load landing page
    @GetMapping
    public String showAddressBook(Model model){
        //Contact c = new....
        model.addAttribute("contact", new Contact());
        return "addressBook";
    }

    ///post with the pathing /contact to be from addressBook.html form action
    //to save the contact information. 
    @PostMapping(consumes="application/x-www-form-urlencoded" , path = "/contact")
    //can also be public String saveAddressBook(HttpServletRequest request, Model model) see day 12. can use requests or object
    public String saveAddressBook(@Valid Contact contact, BindingResult result, Model model){
        System.out.println("Name: "+contact.getName());
        System.out.println("Email: "+contact.getEmail());
        System.out.println("Phone Number: "+contact.getPhoneNumber());
    
     if(result.hasErrors()){
        System.out.println("error count -->" +result.getErrorCount());
     return "addressBook";
     }

     //custom data validation for if there is duplicate email
     if (!Utility.isUniqueEmail(contact.getEmail())){
        ObjectError err = new ObjectError("globalError", "%s is not available as it is already used".formatted(contact.getEmail()));
        result.addError(err);
     }

        return "addressBook";
    }
}

