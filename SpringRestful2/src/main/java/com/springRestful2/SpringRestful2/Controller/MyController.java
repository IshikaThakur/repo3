package com.springRestful2.SpringRestful2.Controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.springRestful2.SpringRestful2.DaoService.UsersDao;
import com.springRestful2.SpringRestful2.ModelClasses.UsersModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@ApiModel(description="Controller class.")
@RestController
public class MyController {
    @Autowired
    MessageSource messageSource;
    @Autowired
    UsersDao usersDao;

    @ApiOperation(value = "To print a welcome message.")
    @GetMapping("/hello")
    public String print() {
        return "Hiiiiii";
    }

   //Ques 4.  Create GET Method to fetch the list of users.
    @GetMapping("/alluser")
    public List<UsersModel> getAllUser(){
        return usersDao.getAll();
    }
  // Ques 8 and Ques 9   Static and Dynamic Filtering

    @ApiOperation(value = "To filter all values except id and name.")
    @GetMapping("/filtering")
    public MappingJacksonValue dFilter() {
        List<UsersModel> list = usersDao.getAll();
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name");
        FilterProvider filters = new SimpleFilterProvider().addFilter("ignoring", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(list);
        mapping.setFilters(filters);
        return mapping;
    }
   //Ques 3:Create POST Method to create user details.
    @PostMapping("/users")
    public void addUser(@RequestBody UsersModel usersModel) {
        UsersModel usersModel1 = usersDao.addUser(usersModel);
    }

    @ApiOperation(value = "To delete a user, and also link to all employees")
    @GetMapping("/users/delete/{id}")
    public EntityModel<UsersModel> deleteOne(@PathVariable int id) {
        UsersModel usersModel = usersDao.deleteUser(id);
        EntityModel<UsersModel> entityModel=new EntityModel<>(usersModel);
        WebMvcLinkBuilder link=WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUser());
        entityModel.add(link.withRel("All-Employees"));
        return entityModel;
    }
     //Ques 1: Add support for Internationalization in your application allowing messages to be shown in English, German and Swedish, keeping English as default

    @GetMapping("/Internationalization_with_name/{id}")
    public String shows(@PathVariable int id, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        UsersModel usersModel = usersDao.getOneUser(id);
        String param[] = {usersModel.getName()};
        return messageSource.getMessage("mrng.message", param, locale);
    }
     //Ques 2:Create a GET request which takes "username" as param and shows a localized message "Hello Username". (Use parameters in message properties)

    @GetMapping("/Internationalization/{id}")
    public String show(@PathVariable int id, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        UsersModel usersModel = usersDao.getOneUser(id);
        String param[] = {
                usersModel.getName()
        };
        return messageSource.getMessage("user.name", param, locale);
    }
}