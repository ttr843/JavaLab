package ru.itis.javalab.ttr.rabbitmqsecond.repository;

import ru.itis.javalab.ttr.rabbitmqsecond.dto.UpdateDto;
import ru.itis.javalab.ttr.rabbitmqsecond.model.User;

public interface UserRepository  extends CrudRepository<Long, User>{
    public void updateByUpdateDto(UpdateDto updateDto);
}
