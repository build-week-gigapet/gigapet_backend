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

    @DeleteMapping("/child/{id}")
    public ResponseEntity<?> deleteChildById(HttpServletRequest request, @PathVariable long id)
    {
        logger.trace(request.getRequestURI() + " accessed");

        childService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/foodentry/{id}")
    public ResponseEntity<?> deleteFoodEntryById(HttpServletRequest request, @PathVariable long id)
    {
        logger.trace(request.getRequestURI() + " accessed");

        foodEntryService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/gigapet/{id}")
    public ResponseEntity<?> deleteGigapetById(HttpServletRequest request, @PathVariable long id)
    {
        logger.trace(request.getRequestURI() + " accessed");

        gigapetService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/child/{id}")
    public ResponseEntity<?> updateChild(HttpServletRequest request, @RequestBody Child updateChild, @PathVariable long id)
    {
        logger.trace(request.getRequestURI() + " accessed");

        Child updatedChild = childService.update(updateChild, id);

        if(updatedChild == null){
            throw new EntityNotFoundException();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/gigapet/{id}")
    public ResponseEntity<?> updateGigapet(HttpServletRequest request, @RequestBody Gigapet updateGigapet, @PathVariable long id)
    {
        logger.trace(request.getRequestURI() + " accessed");

        Gigapet updatedGigapet = gigapetService.update(updateGigapet, id);

        if(updatedGigapet == null){
            throw new EntityNotFoundException();
        }
        return new ResponseEntity<>("billybob",HttpStatus.OK);
    }

    @PutMapping(value = "/foodentry/{id}")
    public ResponseEntity<?> updateFoodEntry(HttpServletRequest request, @RequestBody FoodEntry updateFoodEntry, @PathVariable long id)
    {
        logger.trace(request.getRequestURI() + " accessed");

        FoodEntry updatedFoodEntry = foodEntryService.update(updateFoodEntry, id);

        if(updatedFoodEntry == null){
            throw new EntityNotFoundException();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
