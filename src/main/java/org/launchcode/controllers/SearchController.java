package org.launchcode.controllers;

import com.sun.javafx.collections.ArrayListenerHelper;
import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    @RequestMapping(value = "results", method = RequestMethod.GET)
    public String searchResults(Model model, @RequestParam String column, @RequestParam String value) {
        model.addAttribute("columns", ListController.columnChoices);

        ArrayList jobs;
        boolean error = false;

        if(Objects.equals(column, "all")){
             jobs = JobData.findByValue(value);
        } else {
            jobs =  JobData.findByColumnAndValue(column, value);
            }

        if(jobs.isEmpty()){
            error = true;
        } else {
            model.addAttribute("jobs", jobs);
        }
        model.addAttribute("error", error);
        return "search";

    }
}



