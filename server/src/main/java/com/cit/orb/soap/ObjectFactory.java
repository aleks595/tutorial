//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.02.19 at 02:49:15 PM YEKT 
//


package com.cit.orb.soap;

import com.cit.orb.entity.BankDetails;
import com.cit.orb.entity.ClientsDetails;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {


    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetBankRequest }
     *
     */
    public GetBankRequest createGetBankRequest() {
        return new GetBankRequest();
    }

    /**
     * Create an instance of {@link GetBankResponse }
     *
     */
    public GetBankResponse createGetBankResponse() {
        return new GetBankResponse();
    }

    /**
     * Create an instance of {@link BankDetails }
     *
     */
    public BankDetails createBankDetails() {
        return new BankDetails();
    }

    /**
     * Create an instance of {@link ClientsDetails }
     * 
     */
    public ClientsDetails createClientsDetails() {
        return new ClientsDetails();
    }

}