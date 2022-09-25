package org.launchcode.techjobs.mvc.controllers;

import org.launchcode.techjobs.mvc.models.Job;
import org.launchcode.techjobs.mvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.launchcode.techjobs.mvc.controllers.ListController.columnChoices;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
//    If the user enters “all” in the search box, or if they leave the box empty, call the findAll() method from JobData. Otherwise, send the search information to findByColumnAndValue. In either case, store the results in a jobs ArrayList.
//    Pass jobs into the search.html view via the model parameter.
//    Pass ListController.columnChoices into the view, as the existing search handler does.
    @PostMapping("/results")
    public String searchJobs(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        ArrayList<Job> jobs = new ArrayList<>();
        if (searchType.equals("all") && searchTerm == null) jobs = JobData.findAll();
        else if(searchType.equals("all") && searchTerm != null) jobs=JobData.findByValue(searchTerm);
        else jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        model.addAttribute("title", "Jobs with " + columnChoices.get(searchType) + ": " + searchTerm);
        model.addAttribute("jobs", jobs);
        model.addAttribute("columns", columnChoices);
        return "search";
    }
}

