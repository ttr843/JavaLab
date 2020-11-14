package ru.itis.javalab.ttr.hateoas.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.itis.javalab.ttr.hateoas.controllers.ResourcesController;
import ru.itis.javalab.ttr.hateoas.models.Resource;
import ru.itis.javalab.ttr.hateoas.models.ResourceType;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ResourcesRepresentationProcessor  implements RepresentationModelProcessor<EntityModel<Resource>> {

    @Autowired
    private RepositoryEntityLinks links;


    @Override
    public EntityModel<Resource> process(EntityModel<Resource> model) {
        Resource resource = model.getContent();
        if (resource != null && !resource.getResourceType().equals(ResourceType.UNKNOWN)) {
            model.add(linkTo(methodOn(ResourcesController.class)
            .setResourceTypeUnknown(resource.getId())).withRel("setTypeUnknown"));
        }
        if(resource!=null && resource.getResourceType().equals(ResourceType.UNKNOWN)){
            model.add(links.linkToItemResource(Resource.class,resource.getId()).withRel("delete"));
        }
        return model;
    }
}
