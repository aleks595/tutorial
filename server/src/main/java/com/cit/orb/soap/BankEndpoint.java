package com.cit.orb.soap;

import com.cit.orb.entity.Bank;
import com.cit.orb.entity.BankDetails;
import com.cit.orb.entity.Client;
import com.cit.orb.entity.ClientsDetails;
import com.cit.orb.repository.BankRepository;
import com.cit.orb.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class BankEndpoint {

    public Bank bank;
    @Autowired
    BankRepository bankRepository;
    ClientRepository clientRepository;

    @PayloadRoot(namespace = "http://orbcit.com", localPart = "GetBankRequest")
    @ResponsePayload
    public GetBankResponse processBanksRequest(@RequestPayload GetBankRequest request) {
        GetBankResponse response = new GetBankResponse();
        switch (request.getId()) {
            case 0:
                List<Bank> bank = allBanks();
                bank.forEach(b -> {
                    BankDetails details = new BankDetails();
                    details.setId(b.getId());
                    details.setName(b.getName());
                    response.getBankDetails().add(details);
                });
                break;
            case 1:
                List<Bank> banks = allBanks();
                banks.forEach(b -> {
                    BankDetails details = new BankDetails();
                    details.setId(b.getId());
                    details.setName(b.getName());
                    List<Client> clients = b.getClients();
                    setClientBank(details, clients);
                    response.getBankDetails().add(details);
                });
                break;
            default:
                BankDetails details = new BankDetails();
                details.setId(request.getId());
                details.setName(getBanksName(details.getId()));
                List<Client> clients = bankRepository.findById(request.getId()).getClients();
                setClientBank(details, clients);
                response.getBankDetails().add(details);
                break;
        }
        return response;
    }

    private void setClientBank(BankDetails details, List<Client> clients) {
        clients.forEach(c -> {
            ClientsDetails clientsDetails = new ClientsDetails();
            clientsDetails.setId(c.getId());
            clientsDetails.setFirstName(c.getFirstName());
            clientsDetails.setAddress(c.getAddress());
            clientsDetails.setEmail(c.getEmail());
            clientsDetails.setPhoneNumber(c.getPhoneNumber());
            details.getClients().add(clientsDetails);
        });
    }


    public String getBanksName(int id) {
        bank = bankRepository.findByIdB(id);
        return bank.getName();
    }

    public List<Bank> allBanks() {
        return bankRepository.findAll();
    }
}
