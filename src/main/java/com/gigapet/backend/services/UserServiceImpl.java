package com.gigapet.backend.services;


import com.gigapet.backend.models.*;
import com.gigapet.backend.repository.RoleRepository;
import com.gigapet.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userrepos;

    @Autowired
    private RoleRepository rolerepos;

    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userrepos.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthority());
    }

    public User findUserById(long id) throws EntityNotFoundException {
        return userrepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public void delete(long id) {
        if (userrepos.findById(id).isPresent()) {
            userrepos.deleteById(id);
        } else {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }

    @Transactional
    @Override
    public User save(User user) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPasswordNoEncrypt(user.getPassword());
        newUser.setName(user.getName());

        ArrayList<UserRoles> newRoles = new ArrayList<>();
        for (UserRoles ur : user.getUserRoles()) {
            newRoles.add(new UserRoles(newUser, ur.getRole()));
        }
        newUser.setUserRoles(newRoles);

        if (user.getChildren() != null) {
            for (Child q : user.getChildren()) {
                //TODO: fix save feature
                Child newChild = new Child(q.getName(), newUser, q.getAllergies(), q.getFavorites());

                for (Gigapet g : q.getGigapets()) {
                    newChild.getGigapets().add(new Gigapet(g.getName(), newChild, g.getState()));
                }

                for (FoodEntry f : q.getFoodEntries()) {
                    newChild.getFoodEntries().add(new FoodEntry(newChild, f.getCategory(), f.getDateAdded(), f.getDateChanged(), f.isUsed()));
                }

                newUser.getChildren().add(newChild);
            }
        }

        return userrepos.save(newUser);
    }


    @Transactional
    @Override
    public User update(User updateUser, long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userrepos.findByUsername(authentication.getName());

        if (currentUser != null) {
            if (id == currentUser.getUserid()) {
                if (updateUser.getUsername() != null) {
                    currentUser.setUsername(updateUser.getUsername());
                }

                if (updateUser.getPassword() != null) {
                    currentUser.setPasswordNoEncrypt(updateUser.getPassword());
                }

                if (updateUser.getUserRoles().size() > 0) {
                    // with so many relationships happening, I decided to go
                    // with old school queries
                    // delete the old ones
                    rolerepos.deleteUserRolesByUserId(currentUser.getUserid());

                    // add the new ones
                    for (UserRoles ur : updateUser.getUserRoles()) {
                        rolerepos.insertUserRoles(id, ur.getRole().getRoleid());
                    }
                }

                if (updateUser.getName() != null) {
                    currentUser.setName(updateUser.getName());
                }

                if (updateUser.getChildren() != null) {
                    for (Child q : updateUser.getChildren()) {

                        Child newChild = new Child();

                        if(q.getName() != null){
                            newChild.setName(q.getName());
                        }

                        if(q.getAllergies() != null){
                            newChild.setAllergies(q.getAllergies());
                        }

                        if(q.getFavorites() != null){
                            newChild.setFavorites(q.getFavorites());
                        }

                        if(q.getName() != null){
                            newChild.setName(q.getName());
                        }
                        if(q.getName() != null){
                            newChild.setName(q.getName());
                        }

                        if(q.getGigapets() != null) {
                            for (Gigapet g : q.getGigapets()) {
                                Gigapet newGigapet = new Gigapet();

                                if(g.getName() != null) {
                                    newGigapet.setName(g.getName());
                                }
                                if(g.getState() != 0) {
                                    newGigapet.setState(g.getState());
                                }

                                newGigapet.setChild(newChild);

                                newChild.getGigapets().add(newGigapet);
                            }
                        }

                            for (FoodEntry f : q.getFoodEntries()) {
                                FoodEntry newFoodEntry = new FoodEntry();

                                newFoodEntry.setUsed(f.isUsed());

                                newFoodEntry.setCategory(f.getCategory());

                                newFoodEntry.setDateAdded(f.getDateAdded());

                                newFoodEntry.setDateChanged(System.currentTimeMillis());

                                newChild.getFoodEntries().add(newFoodEntry);
                            }

                            currentUser.getChildren().add(newChild);
                        }
                    }

                return userrepos.save(currentUser);
            } else {
                throw new EntityNotFoundException(Long.toString(id) + " Not current user");
            }
        } else {
            throw new EntityNotFoundException(authentication.getName());
        }

    }
}
