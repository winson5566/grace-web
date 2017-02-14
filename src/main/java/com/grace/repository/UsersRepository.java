package com.grace.repository;

import com.grace.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

/**
 * Created by winson on 13/02/2017.
 */
@Repository
public interface UsersRepository extends JpaRepository<Users, BigInteger> {

}
