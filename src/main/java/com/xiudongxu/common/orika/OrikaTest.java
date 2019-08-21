package com.xiudongxu.common.orika;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * @author dongxu.xiu
 * @since 2019-03-29 下午2:00
 */
public class OrikaTest {


    public static void main(String[] args) {
        MapperFactory mapperFactory =  new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(PersonDTO.class,Person.class)
                .field("firstName","name")
                .field("age","age")
                .byDefault()
                .register();
        MapperFacade mapperFacade = mapperFactory.getMapperFacade();
        PersonDTO personDTO = new PersonDTO("xdx",25,"男");
        Person person = mapperFacade.map(personDTO, Person.class);
        System.out.println(person.toString());
    }

}
