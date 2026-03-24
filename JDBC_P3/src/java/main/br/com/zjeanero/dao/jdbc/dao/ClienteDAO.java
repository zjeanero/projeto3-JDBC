package br.com.zjeanero.dao.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.zjeanero.dao.jdbc.ConnectionFactory;
import br.com.zjeanero.domain.Cliente;

public class ClienteDAO implements IClienteDAO {

	@Override
	public Integer cadastrar(Cliente cliente) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "INSERT INTO TB_CLIENTE (ID, CODIGO, NOME) VALUES (nextval('SQ_CLIENTE'),?,?)";
			stm = connection.prepareStatement(sql);
			stm.setString(1, cliente.getCodigo());
			stm.setString(2, cliente.getNome());
			return stm.executeUpdate();
		} catch(Exception e) {
			throw e;
		} finally {
			if(stm != null && !stm.isClosed()) {
				stm.close();
			}
			if(connection != null && !connection.isClosed()) {
				connection.close();
			}
		}
	}

	@Override
	public Cliente consultar(String codigo) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		Cliente cliente = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "select * from tb_cliente where codigo = ?";
			stm = connection.prepareStatement(sql);
			stm.setString(1, codigo);
			rs = stm.executeQuery();
			if(rs.next()) {
				cliente = new Cliente();
				cliente.setId(rs.getLong("id"));
				cliente.setCodigo(rs.getString("codigo"));
				cliente.setNome(rs.getString("nome"));
			}
			return cliente;
		} catch(Exception e) {
			throw e;
		} finally {
			if(stm != null && !stm.isClosed()) {
				stm.close();
			}
			if(connection != null && !connection.isClosed()) {
				connection.close();
			}
		}
	}

	@Override
	public Integer excluir(Cliente cliente) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "DELETE FROM TB_CLIENTE WHERE CODIGO = ?";
			stm = connection.prepareStatement(sql);
			stm.setString(1, cliente.getCodigo());
			return stm.executeUpdate();
		} catch(Exception e) {
			throw e;
		} finally {
			if(stm != null && !stm.isClosed()) {
				stm.close();
			}
			if(connection != null && !connection.isClosed()) {
				connection.close();
			}
		}
	}

	@Override
	public List<Cliente> buscarTodos() throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		List<Cliente> list = new ArrayList<>();
		Cliente cliente = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "select * from tb_cliente;";
			stm = connection.prepareStatement(sql);
			rs = stm.executeQuery();
			
			while(rs.next()) {
				cliente = new Cliente();
		    	Long id = rs.getLong("ID");
		    	String nome = rs.getString("NOME");
		    	String cd = rs.getString("CODIGO");
		    	cliente.setId(id);
		    	cliente.setNome(nome);
		    	cliente.setCodigo(cd);
		    	list.add(cliente);
			}
		} catch(Exception e) {
			throw e;
		} finally {
			if(stm != null && !stm.isClosed()) {
				stm.close();
			}
			if(connection != null && !connection.isClosed()) {
				connection.close();
			}
		}
		return list;
	}

	@Override
	public Integer atualizar(Cliente cliente) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "UPDATE TB_CLIENTE SET CODIGO = ?, NOME = ? WHERE ID = ?";
			stm = connection.prepareStatement(sql);
			stm.setString(1, cliente.getCodigo());
			stm.setString(2, cliente.getNome());
			stm.setLong(3, cliente.getId());
			return stm.executeUpdate();
		} catch(Exception e) {
			throw e;
		} finally {
			if(stm != null && !stm.isClosed()) {
				stm.close();
			}
			if(connection != null && !connection.isClosed()) {
				connection.close();
			}
		}
	}

}
