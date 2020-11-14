package ru.itis.javalab.ttr.hateoas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.ttr.hateoas.models.Resource;
import ru.itis.javalab.ttr.hateoas.repositories.ResourcesRepository;

@Service
public class ResourcesServiceImpl implements ResourcesService {
    @Autowired
    private ResourcesRepository resourcesRepository;


    @Override
    public Resource setTypeUnknown(Long resourceId) {
        Resource resource = resourcesRepository.findById(resourceId).orElseThrow(IllegalArgumentException::new);
        resource.setTypeUnknown();
        resourcesRepository.save(resource);
        return resource;
    }
}
