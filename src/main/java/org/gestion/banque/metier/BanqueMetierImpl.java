package org.gestion.banque.metier;

import java.util.Date;
import java.util.List;

import org.gestion.banque.dao.IBanqueDao;
import org.gestion.banque.entities.Client;
import org.gestion.banque.entities.Compte;
import org.gestion.banque.entities.Employe;
import org.gestion.banque.entities.Groupe;
import org.gestion.banque.entities.Operation;
import org.gestion.banque.entities.Retrait;
import org.gestion.banque.entities.Versement;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class BanqueMetierImpl implements IBanqueMetier{

	private IBanqueDao dao;
	
	
	
	public void setDao(IBanqueDao dao) {
		this.dao = dao;
	}

	@Override
	public Client addClient(Client c) {
		return dao.addClient(c);
	}

	@Override
	public Employe addEmploye(Employe e, Long codeSup) {
		return dao.addEmploye(e, codeSup);
	}

	@Override
	public Groupe addGroupe(Groupe g) {
		return dao.addGroupe(g);
	}

	@Override
	public void addEmployeToGroup(Long codeEmp, Long codeGr) {
		dao.addEmployeToGroup(codeEmp, codeGr);
	}

	@Override
	public Compte addCompte(Compte cp, long codeCli, Long codeEmp) {
		return dao.addCompte(cp, codeCli, codeEmp);
	}

	@Override
	public void verser(double mt, String cpte, Long codeEmp) {
		dao.addOperation(new Versement(new Date(), mt), cpte, codeEmp);
		Compte cp=dao.consulterComte(cpte);
		cp.setSolde(cp.getSolde()+mt);
		
	}

	@Override
	public void retirer(double mt, String cpte, Long codeEmp) {
		dao.addOperation(new Retrait(new Date(), mt), cpte, codeEmp);
		Compte cp=dao.consulterComte(cpte);
		cp.setSolde(cp.getSolde()-mt);
		
	}

	@Override
	public void virement(double mt, String cpte1, String cpte2, Long codeEmp) {
		retirer(mt, cpte1, codeEmp);
		verser(mt, cpte2, codeEmp);
	}

	@Override
	public void versement(String codeCpte, double mt, Long codeEmp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void retrait(String codeCpte, double mt, Long codeEmp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void virement(String codeCpte1, String codeCpte2, double mt, Long codeEmp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Compte consulterComte(String codeCpte) {
		return dao.consulterComte(codeCpte);
	}

	@Override
	public List<Operation> consulterOperation(String codeCpte) {
		return dao.consulterOperation(codeCpte);
		
	}

	@Override
	public Client consulterClient(Long codeCli) {
		return dao.consulterClient(codeCli);
	}

	@Override
	public List<Client> consulterClients(String mc) {
		return dao.consulterClients(mc);
	}

	@Override
	public List<Compte> getComptesByClient(Long codeCli) {
		return dao.getComptesByClient(codeCli);
	}

	@Override
	public List<Compte> getCompteByEmploye(Long codeEmp) {
		return dao.getCompteByEmploye(codeEmp);
	}

	@Override
	public List<Employe> getEmployes() {
		return dao.getEmployes();
	}

	@Override
	public List<Groupe> getGroupes() {
		return dao.getGroupes();
	}

	@Override
	public List<Employe> getEmployesByGroupe(Long codeGr) {
		return dao.getEmployesByGroupe(codeGr);
	}

}
