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
import model.dao.PessoaFisicaDao;
import model.entities.PessoaFisica;

public class PessoaFisicaDaoJDBC implements PessoaFisicaDao {

	private Connection connection;
	
	public PessoaFisicaDaoJDBC(Connection connection) {
		this.connection = connection;
	}
	
	//insere uma pessoa fisica no banco de dados
	@Override
	public void insert(PessoaFisica obj) {
		
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(
					"INSERT INTO pessoa_fisica "
					+ "(nome, endereco, email, telefone, nascimento, salario, cpf) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getNome());
			st.setString(2, obj.getEndereco());
			st.setString(3, obj.getEmail());
			st.setString(4, obj.getTelefone());
			st.setDate(5, new java.sql.Date(obj.getNascimento().getTime()));
			st.setDouble(6, obj.getSalario());
			st.setString(7, obj.getCpf());
			
			int numeroDeLinhasAfetadas = st.executeUpdate();
			
			if(numeroDeLinhasAfetadas > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					String cpf = rs.getString(1);
					obj.setCpf(cpf);
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

	//lê uma pessoa fisica do banco de dados
	@Override
	public PessoaFisica read(String cpfOuCnpj) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement(
					"SELECT * FROM pessoa_fisica WHERE cpf = ?");
			st.setString(1, cpfOuCnpj);
			rs = st.executeQuery();
			if(rs.next()) {
				PessoaFisica pessoaFisica = new PessoaFisica();
				pessoaFisica.setNome(rs.getString("nome"));
				pessoaFisica.setEndereco(rs.getString("endereco"));
				pessoaFisica.setEmail(rs.getString("email"));
				pessoaFisica.setTelefone(rs.getString("telefone"));
				pessoaFisica.setNascimento(rs.getDate("nascimento"));
				pessoaFisica.setSalario(rs.getDouble("salario"));
				pessoaFisica.setCpf(rs.getString("cpf"));
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

	//lê todas as pessoas fisicas no banco de dados
	@Override
	public List<PessoaFisica> readAll() {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement("SELECT * FROM pessoa_fisica");
			rs = st.executeQuery();
			
			List<PessoaFisica> list = new ArrayList<>();
			
			while(rs.next()) {
				PessoaFisica pessoaFisica = new PessoaFisica();
				pessoaFisica.setNome(rs.getString("nome"));
				pessoaFisica.setEndereco(rs.getString("endereco"));
				pessoaFisica.setEmail(rs.getString("email"));
				pessoaFisica.setTelefone(rs.getString("telefone"));
				pessoaFisica.setNascimento(rs.getDate("nascimento"));
				pessoaFisica.setSalario(rs.getDouble("salario"));
				pessoaFisica.setCpf(rs.getString("cpf"));
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

	//atualiza os dados de uma pessoa fisica no banco de dados
	@Override
	public void update(PessoaFisica obj) {
		
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(
					"UPDATE pessoa_fisica "
					+ "SET nome = ?, endereco = ?, email = ?, telefone = ?, "
					+ "nascimento = ?, salario = ?, cpf = ? "
					+ "WHERE cpf = ?");
			st.setString(1, obj.getNome());
			st.setString(2, obj.getEndereco());
			st.setString(3, obj.getEmail());
			st.setString(4, obj.getTelefone());
			st.setDate(5, new java.sql.Date(obj.getNascimento().getTime()));
			st.setDouble(6, obj.getSalario());
			st.setString(7, obj.getCpf());
			st.executeUpdate();	
		}
		catch(SQLException e) {
			throw new DBException(e.getMessage());
		}
		finally {
			DBConnection.closeStatement(st);
		}
	}

	//deleta dos dados de uma pessoa fisica do banco de dados
	@Override
	public void delete(String cpfOuCnpj) {
		
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement("DELETE FROM pessoa_fisica WHERE cpf = ?");
			st.setString(1, cpfOuCnpj);
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
