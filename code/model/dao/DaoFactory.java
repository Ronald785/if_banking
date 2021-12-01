package model.dao;

import dbConnection.DBConnection;
import model.dao.implementations.ContaDaoJDBC;
import model.dao.implementations.DepositoDaoJDBC;
import model.dao.implementations.PessoaFisicaDaoJDBC;
import model.dao.implementations.PessoaJuridicaDaoJDBC;
import model.dao.implementations.SaqueDaoJDBC;
import model.dao.implementations.TaxaDaoJDBC;
import model.dao.implementations.TransferenciaDaoJDBC;

public class DaoFactory {

	public static PessoaFisicaDao createPessoaFisicaDao() {
		return new PessoaFisicaDaoJDBC(DBConnection.getConnection());
	}
	
	public static PessoaJuridicaDao createPessoaJuridicaDao() {
		return new PessoaJuridicaDaoJDBC(DBConnection.getConnection());
	}
	
	public static ContaDao createContaDao() {
		return new ContaDaoJDBC(DBConnection.getConnection());
	}
	
	public static DepositoDao createDepositoDao() {
		return new DepositoDaoJDBC(DBConnection.getConnection());
	}
	
	public static SaqueDao createSaqueDao() {
		return new SaqueDaoJDBC(DBConnection.getConnection());
	}
	
	public static TaxaDao createTaxaDao() {
		return new TaxaDaoJDBC(DBConnection.getConnection());
	}
	
	public static TransferenciaDao createTransferenciaDao() {
		return new TransferenciaDaoJDBC(DBConnection.getConnection());
	}
}
