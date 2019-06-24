package com.gigapet.backend.controllers;


import com.gigapet.backend.models.Parent;
import com.gigapet.backend.services.ParentService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.slf4j.Logger;


@RestController
@RequestMapping("/parents")
public class ParentController
{
    private static final Logger logger = LoggerFactory.getLogger(RolesController.class);

    @Autowired
    ParentService parentService;

    @GetMapping(value = "/parents", produces = {"application/json"})
    public ResponseEntity<?> listAllParents(HttpServletRequest request)
    {
        logger.trace(request.getRequestURI() + " accessed");

        List<Parent> allParents = parentService.findAll();
        return new ResponseEntity<>(allParents, HttpStatus.OK);
    }


    @GetMapping(value = "/parent/{parentId}", produces = {"application/json"})
    public ResponseEntity<?> getParent(HttpServletRequest request, @PathVariable
                                              Long parentId)
    {
        logger.trace(request.getRequestURI() + " accessed");

        Parent q = parentService.findParentById(parentId);
        return new ResponseEntity<>(q, HttpStatus.OK);
    }


    @GetMapping(value = "/username/{userName}", produces = {"application/json"})
    public ResponseEntity<?> findParentByUserName(HttpServletRequest request, @PathVariable
                                                         String userName)
    {
        logger.trace(request.getRequestURI() + " accessed");

        List<Parent> theParents = parentService.findByUserName(userName);
        return new ResponseEntity<>(theParents, HttpStatus.OK);
    }



    @PostMapping(value = "/parent")
    public ResponseEntity<?> addNewParent(HttpServletRequest request, @Valid @RequestBody
                                                 Parent newParent) throws URISyntaxException
    {
        logger.trace(request.getRequestURI() + " accessed");

        newParent = parentService.save(newParent);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newParentURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{parentid}")
                .buildAndExpand(newParent.getParentid())
                .toUri();
        responseHeaders.setLocation(newParentURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }


    @DeleteMapping("/parent/{id}")
    public ResponseEntity<?> deleteParentById(HttpServletRequest request, @PathVariable
                                                     long id)
    {
        logger.trace(request.getRequestURI() + " accessed");

        parentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
