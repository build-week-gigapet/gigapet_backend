package com.gigapet.backend.controllers;


import com.gigapet.backend.models.Child;
import com.gigapet.backend.models.FoodEntry;
import com.gigapet.backend.models.Gigapet;
import com.gigapet.backend.models.User;
import com.gigapet.backend.services.ChildService;
import com.gigapet.backend.services.FoodEntryService;
import com.gigapet.backend.services.GigapetService;
import com.gigapet.backend.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/child")
public class ChildController {
    private static final Logger logger = LoggerFactory.getLogger(RolesController.class);

    @Autowired
    ChildService childService;

    @Autowired
    UserService userService;

    @Autowired
    GigapetService gigapetService;

    @Autowired
    FoodEntryService foodEntryService;

    @GetMapping(value = "/children", produces = {"application/json"})
    public ResponseEntity<?> listAllChildren(HttpServletRequest request) {
        logger.trace(request.getRequestURI() + " accessed");

        List<Child> allChildren = childService.findAll();
        return new ResponseEntity<>(allChildren, HttpStatus.OK);
    }


    @GetMapping(value = "/child/{childid}", produces = {"application/json"})
    public ResponseEntity<?> getChildById(@PathVariable long childid, HttpServletRequest request) {
        logger.trace(request.getRequestURI() + " accessed");

        Child child = childService.findChildById(childid);
        return new ResponseEntity<>(child, HttpStatus.OK);
    }


    @GetMapping(value = "/child/{childid}/gigapets", produces = {"application/json"})
    public ResponseEntity<?> listAllGigapets(@PathVariable long childid, HttpServletRequest request) {
        logger.trace(request.getRequestURI() + " accessed");

        List<Gigapet> allGigapets = childService.findChildById(childid).getGigapets();
        return new ResponseEntity<>(allGigapets, HttpStatus.OK);
    }


    @GetMapping(value = "/gigapet/{gigapetid}", produces = {"application/json"})
    public ResponseEntity<?> getGigapetById(@PathVariable long gigapetid, HttpServletRequest request) {
        logger.trace(request.getRequestURI() + " accessed");

        Gigapet gigapet = gigapetService.findGigapetById(gigapetid);
        return new ResponseEntity<>(gigapet, HttpStatus.OK);
    }

    @GetMapping(value = "/child/{childid}/foodentries", produces = {"application/json"})
    public ResponseEntity<?> listAllFoodEntries(@PathVariable long childid, HttpServletRequest request) {
        logger.trace(request.getRequestURI() + " accessed");

        List<FoodEntry> allFoodEntries = childService.findChildById(childid).getFoodEntries();
        return new ResponseEntity<>(allFoodEntries, HttpStatus.OK);
    }

    @GetMapping(value = "/foodentry/{foodentryid}", produces = {"application/json"})
    public ResponseEntity<?> getFoodEntryById(@PathVariable long foodentryid, HttpServletRequest request) {
        logger.trace(request.getRequestURI() + " accessed");

        FoodEntry foodEntry = foodEntryService.findFoodEntryById(foodentryid);
        return new ResponseEntity<>(foodEntry, HttpStatus.OK);
    }

    @PostMapping(value = "/createnewchild/user/{userid}")
    public ResponseEntity<?> createNewChild(@PathVariable long userid, @RequestBody Child newChild) {
        User user = userService.findUserById(userid);
        if (newChild == null || user == null) {
            throw new EntityNotFoundException();
        }
        newChild.setUser(user);
        user.getChildren().add(newChild);
        userService.update(user, userid);

        return new ResponseEntity<>(newChild.getChildid(),HttpStatus.CREATED);
}

}
