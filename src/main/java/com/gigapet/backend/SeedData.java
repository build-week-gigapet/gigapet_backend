package com.gigapet.backend;

import com.gigapet.backend.models.*;
import com.gigapet.backend.services.RoleService;
import com.gigapet.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
@Component
public class SeedData implements CommandLineRunner
{
    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;


    @Override
    public void run(String[] args) throws Exception
    {
        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role("data");

        roleService.save(r1);
        roleService.save(r2);
        roleService.save(r3);

        // admin, data, user


        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(), r1));
        admins.add(new UserRoles(new User(), r2));
        admins.add(new UserRoles(new User(), r3));


        User u1 = new User("admin", "password", admins, "Johnny Boy");
       // u1.setUsername("Johnny Boy");


        Child child1 = new Child("Timmy");
        child1.setFavorites("All The Meats!");
        child1.setAllergies("Vegetables!");
       child1.getGigapets().add(new Gigapet("BillyBob", child1, 1));
        child1.getGigapets().add(new Gigapet("CindyLou", child1, 1));
       child1.getFoodEntries().add(new FoodEntry(child1, 1, System.currentTimeMillis(), System.currentTimeMillis(), false));
         child1.getFoodEntries().add(new FoodEntry(child1, 2, System.currentTimeMillis(), System.currentTimeMillis(), false));
        child1.getFoodEntries().add(new FoodEntry(child1, 2, System.currentTimeMillis(), System.currentTimeMillis(), false));

        u1.getChildren().add(child1);

        userService.save(u1);



        User u2 = new User("admin2", "password", admins, "Little Tony");
        // u2.setUsername("Johnny Boy");


        Child child2 = new Child("Timmy2");
        child2.setFavorites("All The Meats!2");
        child2.setAllergies("Vegetables!2");
        child2.getGigapets().add(new Gigapet("BillyBob2", child2, 1));
        child2.getGigapets().add(new Gigapet("CindyLou2", child2, 1));
        child2.getGigapets().add(new Gigapet("RickyGervais", child2, 3));
        child2.getFoodEntries().add(new FoodEntry(child2, 2, System.currentTimeMillis(), System.currentTimeMillis(), false));
        child2.getFoodEntries().add(new FoodEntry(child2, 3, System.currentTimeMillis(), System.currentTimeMillis(), false));
        child2.getFoodEntries().add(new FoodEntry(child2, 4, System.currentTimeMillis(), System.currentTimeMillis(), false));
        child2.getFoodEntries().add(new FoodEntry(child2, 4, System.currentTimeMillis(), System.currentTimeMillis(), false));
        child2.getFoodEntries().add(new FoodEntry(child2, 4, System.currentTimeMillis(), System.currentTimeMillis(), false));


        Child child4 = new Child("BamBam");
        child4.setFavorites("All The Meats!");
        child4.setAllergies("Vegetables!");
        child4.getGigapets().add(new Gigapet("BillyBob4", child4, 1));
        child4.getGigapets().add(new Gigapet("CindyLou4", child4, 1));
        child4.getFoodEntries().add(new FoodEntry(child4, 3, System.currentTimeMillis(), System.currentTimeMillis(), false));
        child4.getFoodEntries().add(new FoodEntry(child4, 1, System.currentTimeMillis(), System.currentTimeMillis(), false));
        child4.getFoodEntries().add(new FoodEntry(child4, 5, System.currentTimeMillis(), System.currentTimeMillis(), false));

        u2.getChildren().add(child2);
        u2.getChildren().add(child4);

        userService.save(u2);




        User u3 = new User("admin3", "password", admins, "Little Tony2");
        // u2.setUsername("Johnny Boy");


        Child child3 = new Child("Timmy3");
        child3.setFavorites("All The Meats!3");
        child3.setAllergies("Vegetables!3");
        child3.getGigapets().add(new Gigapet("RickyGervais", child3, 3));
        child3.getFoodEntries().add(new FoodEntry(child3, 2, System.currentTimeMillis(), System.currentTimeMillis(), false));
        child3.getFoodEntries().add(new FoodEntry(child3, 5, System.currentTimeMillis(), System.currentTimeMillis(), false));

        u3.getChildren().add(child3);

        userService.save(u3);

   /*     // data, user
        ArrayList<UserRoles> datas = new ArrayList<>();
        datas.add(new UserRoles(new User(), r3));
        datas.add(new UserRoles(new User(), r2));
        User u2 = new User("cinnamon", "1234567", datas);
        userService.save(u2);

        // user
        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u3 = new User("barnbarn", "ILuvM4th!", users);
        u3.getParents().add(new Parent("John", u3));
        u3.getParents().add(new Parent("Kelly", u3));
        userService.save(u3);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u4 = new User("Bob", "password", users);
        userService.save(u4);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u5 = new User("Jane", "password", users);
        userService.save(u5);*/



   /*     Parent parent1 = new Parent("John", u1);
        Child child4 = new Child("Timmy");
        child1.getGigapets().add(new Gigapet("BillyBob", child1, 1));
        child1.getGigapets().add(new Gigapet("CindyLou", child1, 1));
        child1.getFoodEntries().add(new FoodEntry(child1, 1, System.currentTimeMillis(), System.currentTimeMillis(), false));
        child1.getFoodEntries().add(new FoodEntry(child1, 2, System.currentTimeMillis(), System.currentTimeMillis(), false));
        child1.getFoodEntries().add(new FoodEntry(child1, 2, System.currentTimeMillis(), System.currentTimeMillis(), false));
        parent1.getParentChild().add(new ParentChild(parent1,child1));*/
         //userService.save(u1);



/*        Parent parent2 = new Parent("John2", u2);
        Child child2 = new Child("Timmy2");
        child2.getGigapets().add(new Gigapet("BillyBob2", child2, 1));
        child2.getGigapets().add(new Gigapet("CindyLou2", child2, 1));
        child2.getFoodEntries().add(new FoodEntry(child2, 1, System.currentTimeMillis(), System.currentTimeMillis(), false));
        child2.getFoodEntries().add(new FoodEntry(child2, 2, System.currentTimeMillis(), System.currentTimeMillis(), false));
        child2.getFoodEntries().add(new FoodEntry(child2, 2, System.currentTimeMillis(), System.currentTimeMillis(), false));
        parent2.getParentChild().add(new ParentChild(parent2,child2));

        Parent parent3 = new Parent("John3", u2);
        Child child3 = new Child("Timmy3");
        child3.getGigapets().add(new Gigapet("BillyBob3", child3, 1));
        child3.getGigapets().add(new Gigapet("CindyLou3", child3, 1));
        child3.getFoodEntries().add(new FoodEntry(child3, 1, System.currentTimeMillis(), System.currentTimeMillis(), false));
        child3.getFoodEntries().add(new FoodEntry(child3, 2, System.currentTimeMillis(), System.currentTimeMillis(), false));
        child3.getFoodEntries().add(new FoodEntry(child3, 2, System.currentTimeMillis(), System.currentTimeMillis(), false));
        parent3.getParentChild().add(new ParentChild(parent3,child3));

        userService.save(u2);*/
    }
}