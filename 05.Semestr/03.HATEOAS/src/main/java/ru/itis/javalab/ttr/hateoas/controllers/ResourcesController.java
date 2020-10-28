package ru.itis.javalab.ttr.hateoas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab.ttr.hateoas.services.ResourcesService;

@RepositoryRestController
public class ResourcesController {

    @Autowired
    private ResourcesService resourcesService;

    @RequestMapping(value = "/resources/{resource-id}/updateRate" , method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<?> updateRate(@PathVariable("resource-id") Long resourceId,
                                 @RequestParam("rateValue") double rateValue){
        return ResponseEntity.ok(EntityModel.of(resourcesService.updateRate(resourceId,rateValue)));
    }

    @RequestMapping(value = "/resources/{resource-id}/setTypeUnknown" , method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<?> setResourceTypeUnknown(@PathVariable("resource-id") Long resourceId){
        return ResponseEntity.ok(EntityModel.of(resourcesService.setTypeUnknown(resourceId)));
    }
}
