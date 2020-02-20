package com.cit.orb.controller;
import com.cit.orb.OrbApplication;
import com.cit.orb.entity.Bank;
import com.cit.orb.entity.Client;

import com.cit.orb.repository.BankRepository;
import com.cit.orb.repository.ClientRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.coyote.Response;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;



@RestController
@RequestMapping(value="/bank")
public class BankController {

    @Autowired
    BankRepository repository;
    ClientRepository clientRepository;

    @RequestMapping(value="/get",method = RequestMethod.GET)
    public Bank get(@RequestParam int id) {
        return repository.findByIdB(id);
    }

   /* @RequestMapping(value="/gets",method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    public Bank getXml(@RequestParam int id) {
        return repository.findByIdB(id);
    }
    */

   @RequestMapping(method = RequestMethod.POST)
   public Bank save(@RequestBody Bank bank) {
        return repository.save(bank);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void delete(@RequestParam int id) {
        clientRepository.updateBankId(id);
        repository.deleteBank(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public void rename(@RequestParam int id, @RequestParam String name) {
        repository.updateNameBank(id,name);
    }
}



