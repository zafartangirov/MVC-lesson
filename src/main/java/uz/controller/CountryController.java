package uz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uz.model.Country;
import uz.service.DatabaseService;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/country")
public class CountryController {
    @Autowired
    DatabaseService databaseService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String getCountryList(Model model) throws SQLException, ClassNotFoundException {
        List<Country> countryList=databaseService.getCountryList();
        model.addAttribute("davlatlar",countryList);
        return "country";


    }
}