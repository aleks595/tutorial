package com.cit.orb.repository;

import com.cit.orb.entity.Bank;
import com.cit.orb.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {

     @Modifying
     @Transactional
     @Query("delete from Client c where c.id=:id")
     void deleteClient(@Param("id") int id);

     @Modifying
     @Transactional
     @Query (value="UPDATE Client c SET c.bank = null where c.bank.id = :bankId")
     void updateBankId(@Param("bankId") int bankId);

     @Query (nativeQuery=true, value="select Client.* from Client where id=:id")
     Client findClient(@Param("id") int id);

     @Query (nativeQuery=true, value="Select client.* from client Inner Join bank ON client.bank_id = bank.id where bank.name=:name")
     List<Client> findByBankName(@Param("name") String name);
}
