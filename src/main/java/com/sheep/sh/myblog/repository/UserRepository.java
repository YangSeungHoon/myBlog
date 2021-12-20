package com.sheep.sh.myblog.repository;

import com.sheep.sh.myblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}


//User findByUsernameAndPassword(String username, String password);

//    @Query(value="SELECT * FROM userWHERE usernmae=?1 AND passowrd =?2",nativeQuery = true)
//    User login(String username,String password);