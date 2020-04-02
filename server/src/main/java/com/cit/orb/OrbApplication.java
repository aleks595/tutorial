package com.cit.orb;

import com.cit.orb.entity.Bank;
import com.cit.orb.entity.Client;
import com.cit.orb.file.MyFilesOperation;
import com.cit.orb.repository.BankRepository;
import com.cit.orb.repository.ClientRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import java.io.*;
import java.util.List;

@SpringBootApplication
public class OrbApplication {

	public static void main(String[] args) throws IOException {
		ClientAndBank myClass = new ClientAndBank();
		//myClass.newBankSave("");
		//myClass.newBankSave("");
	    //System.out.println(myClass.findByNameBank(""));
		//myClass.getTestFile();
		//String fileName="myFile.txt";
		//String text = "Hello wold I am!";
	    //MyFilesOperation myClassFile = new MyFilesOperation(fileName);
		//myClass.newBankSave("");
	}

	public static class ClientAndBank {
		public int id;
		public Bank bank;
		ConfigurableApplicationContext context = SpringApplication.run(OrbApplication.class);
		BankRepository bankRepository = context.getBean(BankRepository.class);
		ClientRepository clientRepository = context.getBean(ClientRepository.class);

		public ClientAndBank() {
			this.id = id;
		}

		public void clientAll() {
			System.out.println(clientRepository.findAll());
		}

		public void printCL(int id) {
			System.out.println(bankRepository.findById(id));
		}

		/*public void ClientSave(int id, String firstN, String lastN, String phone, String address, String email) {
			Client b = new Client(firstN, lastN, phone, address, email);
			b = clientRepository.save(b);
			b.setBank(bankRepository.findById(id));
			clientRepository.save(b);
		}*/

		public void BankClient(int id) {
			bank = bankRepository.findByIdB(id);
			List<Client> clients = bank.getClients();
			clients.forEach(c -> {
				System.out.println(c);
			});
		}

		public void editBank(int id, String name) {
			bankRepository.updateNameBank(id, name);
		}

		public void updateBank(int id, String newName) {
			bank = bankRepository.findByIdB(id);
			bank.setName(newName);
			bankRepository.save(bank);
		}

		public void deleteClient(int id) {
			clientRepository.deleteClient(id);
		}

		public void del(int id) {
			clientRepository.updateBankId(id);
			bankRepository.deleteBank(id);
		}

		public void findCl1() {
			System.out.println(clientRepository.findClient(29));
		}

		public List<Client> findByNameBank (String name){
			return clientRepository.findByBankName(name);
		}

		public void newBankSave(String name) {
			Bank myBank = new Bank();
			myBank.setName(name);
			bankRepository.save(myBank);
		}
	}
}
