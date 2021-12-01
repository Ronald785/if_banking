package model.dao.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dbConnection.DBConnection;
import dbConnection.DBException;
import model.dao.PessoaJuridicaDao;
import model.entities.PessoaJuridica;

public class PessoaJuridicaDaoJDBC implements PessoaJuridicaDao {

	private Connection connection;
	
	public PessoaJuridicaDaoJDBC(Connection connection) {
		this.connection = connection;
	}
	
	//insere uma pessoa juridica no banco de dados
	@Override
	public void insert(PessoaJuridica obj) {
		
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(
					"INSERT INTO pessoa_juridica "
					+ "(nome, endereco, email, telefone, nascimento, salario, cnpj) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getNome());
			st.setString(2, obj.getEndereco());
			st.setString(3, obj.getEmail());
			st.setString(4, obj.getTelefone());
			st.setDate(5, new java.sql.Date(obj.getNascimento().getTime()));
			st.setDouble(6, obj.getSalario());
			st.setString(7, obj.getCnpj());
			
			int numeroDeLinhasAfetadas = st.executeUpdate();
			
			if(numeroDeLinhasAfetadas > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					String cnpj = rs.getString(1);
					obj.setCnpj(cnpj);
				}
				DBConnection.closeResultSet(rs);
			}
			else {
				throw new DBException("Erro, nenhuma linha afetada");
			}
		}
		catch(SQLException e) {
			throw new DBException(e.getMessage());
		}
		finally {
			DBConnection.closeStatement(st);
		}
	}

	//lê uma pessoa juridica do banco de dados
	@Override
	public PessoaJuridica read(String cnpjOuCnpj) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement(
					"SELECT * FROM pessoa_fisica WHERE cnpj = ?");
			st.setString(1, cnpjOuCnpj);
			rs = st.executeQuery();
			if(rs.next()) {
				PessoaJuridica pessoaFisica = new PessoaJuridica();
				pessoaFisica.setNome(rs.getString("nome"));
				pessoaFisica.setEndereco(rs.getString("endereco"));
				pessoaFisica.setEmail(rs.getString("email"));
				pessoaFisica.setTelefone(rs.getString("telefone"));
				pessoaFisica.setNascimento(rs.getDate("nascimento"));
				pessoaFisica.setSalario(rs.getDouble("salario"));
				pessoaFisica.setCnpj(rs.getString("cnpj"));
				return pessoaFisica;
			}
			return null;
		}
		catch(SQLException e) {
			throw new DBException(e.getMessage());
		}
		finally {
			DBConnection.closeStatement(st);
			DBConnection.closeResultSet(rs);
		}
	}

	//lê todas as pessoas juridicas no banco de dados
	@Override
	public List<PessoaJuridica> readAll() {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement("SELECT * FROM pessoa_fisica");
			rs = st.executeQuery();
			
			List<PessoaJuridica> list = new ArrayList<>();
			
			while(rs.next()) {
				PessoaJuridica pessoaFisica = new PessoaJuridica();
				pessoaFisica.setNome(rs.getString("nome"));
				pessoaFisica.setEndereco(rs.getString("endereco"));
				pessoaFisica.setEmail(rs.getString("email"));
				pessoaFisica.setTelefone(rs.getString("telefone"));
				pessoaFisica.setNascimento(rs.getDate("nascimento"));
				pessoaFisica.setSalario(rs.getDouble("salario"));
				pessoaFisica.setCnpj(rs.getString("cnpj"));
				list.add(pessoaFisica);
			}
			return list;
		}
		catch(SQLException e) {
			throw new DBException(e.getMessage());
		}
		finally {
			DBConnection.closeStatement(st);
			DBConnection.closeResultSet(rs);
		}
	}

	//atualiza os dados de uma pessoa juridica no banco de dados
	@Override
	public void update(PessoaJuridica obj) {
		
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(
					"UPDATE pessoa_fisica "
					+ "SET nome = ?, endereco = ?, email = ?, telefone = ?, "
					+ "nascimento = ?, salario = ?, cnpj = ? "
					+ "WHERE cnpj = ?");
			st.setString(1, obj.getNome());
			st.setString(2, obj.getEndereco());
			st.setString(3, obj.getEmail());
			st.setString(4, obj.getTelefone());
			st.setDate(5, new java.sql.Date(obj.getNascimento().getTime()));
			st.setDouble(6, obj.getSalario());
			st.setString(7, obj.getCnpj());
			st.executeUpdate();	
		}
		catch(SQLException e) {
			throw new DBException(e.getMessage());
		}
		finally {
			DBConnection.closeStatement(st);
		}
	}

	//deleta dos dados de uma pessoa juridica do banco de dados
	@Override
	public void delete(String cnpjOuCnpj) {
		
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement("DELETE FROM pessoa_fisica WHERE cnpj = ?");
			st.setString(1, cnpjOuCnpj);
			st.executeUpdate();
		}
		catch(SQLException e) {
			throw new DBException(e.getMessage());
		}
		finally {
			DBConnection.closeStatement(st);
		}
	}
}
