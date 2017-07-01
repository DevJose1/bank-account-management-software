package org.gestion.banque.dao;

import java.util.List;

import javax.management.RuntimeErrorException;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.gestion.banque.entities.Client;
import org.gestion.banque.entities.Compte;
import org.gestion.banque.entities.Employe;
import org.gestion.banque.entities.Groupe;
import org.gestion.banque.entities.Operation;

public class BanqueDaoIml implements IBanqueDao{
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Client addClient(Client c) {
		em.persist(c);
		return c;
	}

	@Override
	public Employe addEmploye(Employe e, Long codeSup) {
		if (codeSup!= null){
			Employe sup =em.find(Employe.class, codeSup);
			e.setEmployeSup(sup);
		}
		em.persist(e);
		return e;
				
	}

	@Override
	public Groupe addGroupe(Groupe g) {
		em.persist(g);
		
		return g;
	}

	@Override
	public void addEmployeToGroup(Long codeEmp, Long codeGr) {
		Employe e=em.find(Employe.class, codeEmp);
		Groupe g =em.find(Groupe.class, codeGr);
		e.getGroupes().add(g);
		g.getEmployes().add(e);
		

		
	}

	@Override
	public Compte addCompte(Compte cp, long codeCli, Long codeEmp) {
		Client cli =em.find(Client.class, codeCli);
		Employe emp=em.find(Employe.class, codeEmp);
		cp.setClient(cli);
		cp.setEmploye(emp);
		em.persist(cp);
		
		return cp;
	}

	@Override
	public Operation addOperation(Operation op, String codeCpte, Long codeEmp) {
		Compte cp=consulterComte(codeCpte);
		Employe emp=em.find(Employe.class, codeEmp);
		op.setCompte(cp);
		op.setEmploye(emp);
		em.persist(op);
		return op;
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
		Compte cp=em.find(Compte.class, codeCpte);
		if (cp==null) throw new RuntimeException("Compte inexistant");
		return cp;
	}

	@Override
	public List<Operation> consulterOperation(String codeCpte) {
		Query req = em.createQuery("select o from Operation  where o.compte.codeCompte=:x");
		req.setParameter("x", codeCpte);
		
		return req.getResultList();
	}

	@Override
	public Client consulterClient(Long codeCli) {
		Client c=em.find(Client.class, codeCli);
		if (c==null) throw new RuntimeException("Client introuvable");
		return c;
	
	}

	@Override
	public List<Client> consulterClients(String mc) {
		Query req = em.createQuery("select c from Client  where c.nomClient.codeCompte like :x");
		req.setParameter("x", "%"+ mc+"%");
		
		return req.getResultList();	}

	@Override
	public List<Compte> getComptesByClient(Long codeCli) {
		Query req = em.createQuery("select c from Compte  where c.Client.codeCompte =:x");
		req.setParameter("x", codeCli);
		
		return req.getResultList();	
	}

	@Override
	public List<Compte> getCompteByEmploye(Long codeEmp) {
		Query req = em.createQuery("select c from Compte  where c.Employe.codeCompte =:x");
		req.setParameter("x", codeEmp);
		
		return req.getResultList();	
	}
	

	@Override
	public List<Employe> getEmployes() {
		Query req = em.createQuery("select e from Employe");
		return req.getResultList();	
	}

	@Override
	public List<Groupe> getGroupes() {	
		Query req = em.createQuery("select g from Group");
		return req.getResultList();	
		
	}

	@Override
	public List<Employe> getEmployesByGroupe(Long codeGr) {
		Query req = em.createQuery("select e from Employe  where e.goupes.codeGr =:x");
		req.setParameter("x", codeGr);
		
		return req.getResultList();
	}

}
