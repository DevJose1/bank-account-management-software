package org.gestion.banque.dao;

import java.util.List;

import org.gestion.banque.entities.Client;
import org.gestion.banque.entities.Compte;
import org.gestion.banque.entities.Employe;
import org.gestion.banque.entities.Groupe;
import org.gestion.banque.entities.Operation;

public interface IBanqueDao {
	public Client addClient(Client c);
	public Employe addEmploye(Employe e, Long codeSup );
	public Groupe addGroupe(Groupe g);
	public void addEmployeToGroup(Long codeEmp, Long codeGr);
	public Compte addCompte(Compte cp, long codeCli, Long codeEmp);
	public Operation addOperation(Operation op, String codeCpte, 
			Long codeEmp);
	public void versement(String codeCpte, double mt, Long codeEmp);
	public void retrait(String codeCpte, double mt, Long codeEmp);
	public void virement(String codeCpte1, String codeCpte2,
			double mt, Long codeEmp);
	public Compte consulterComte(String codeCpte);
	public List<Operation> consulterOperation(String codeCpte);
	public Client consulterClient(Long codeCli);
	public List<Client> consulterClients(String mc);
	public List<Compte> getComptesByClient(Long codeCli);
	public List<Compte> getCompteByEmploye(Long codeEmp);
	public List<Employe> getEmployes();
	public List<Groupe> getGroupes();
	public List<Employe> getEmployesByGroupe(Long codeGr);
	
	
	

	

	
	
	

}
