package com.cit.orb.repository;

import com.cit.orb.entity.Bank;
import com.cit.orb.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public  interface BankRepository extends JpaRepository<Bank, Integer>{

  Bank findById(int id);

  @Query (nativeQuery=true, value="select Bank.* from Bank  where id = :id")
  Bank findByIdB(@Param("id") int id);

  @Modifying
  @Transactional
  @Query (value="UPDATE Bank b SET b.name = :name where b.id = :id")
   void updateNameBank(@Param("id") int id,
                       @Param("name") String name);

  @Modifying
  @Transactional
  @Query (value="delete from Bank b where b.id = :id")
   void deleteBank(@Param("id") int id);
}