package de.tuchemnitz.de.Main.repository;



import de.tuchemnitz.de.Main.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

//import hello.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {

    @Transactional
    @Modifying
    public int updateByFeedage(int feedage, int id);

}
