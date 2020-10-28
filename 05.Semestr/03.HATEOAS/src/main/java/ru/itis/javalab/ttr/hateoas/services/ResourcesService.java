package ru.itis.javalab.ttr.hateoas.services;

import ru.itis.javalab.ttr.hateoas.models.Resource;

public interface ResourcesService {
    Resource updateRate(Long resourceId,double rate);
    Resource setTypeUnknown(Long resourceId);
}
