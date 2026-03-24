package br.com.zjeanero.dao.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.zjeanero.dao.jdbc.ConnectionFactory;
import br.com.zjeanero.domain.Cliente;
import br.com.zjeanero.domain.Produto;

public class ProdutoDAO implements IProdutoDAO {

	@Override
	public Integer cadastrar(Produto produto) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "INSERT INTO TB_PRODUTO (ID, CODIGO, NOME, PRECO) VALUES (nextval('SQ_PRODUTO'),?,?,?)";
			stm = connection.prepareStatement(sql);
			stm.setString(1, produto.getCodigo());
			stm.setString(2, produto.getNome());
			stm.setLong(3, produto.getPreco());
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
	public Produto consultar(String codigo) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		Produto produto = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "select * from tb_produto where codigo = ?";
			stm = connection.prepareStatement(sql);
			stm.setString(1, codigo);
			rs = stm.executeQuery();
			if(rs.next()) {
				produto = new Produto();
				produto.setId(rs.getLong("id"));
				produto.setCodigo(rs.getString("codigo"));
				produto.setNome(rs.getString("nome"));
				produto.setPreco(rs.getLong("preco"));
			}
			return produto;
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
	public Integer excluir(Produto produto) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "delete from tb_produto where codigo = ?";
			stm = connection.prepareStatement(sql);
			stm.setString(1, produto.getCodigo());
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
	public List<Produto> buscarTodos() throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		List<Produto> list = new ArrayList<>();
		Produto produto = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "select * from tb_produto;";
			stm = connection.prepareStatement(sql);
			rs = stm.executeQuery();
			
			while(rs.next()) {
				produto = new Produto();
		    	Long id = rs.getLong("ID");
		    	String nome = rs.getString("NOME");
		    	String cd = rs.getString("CODIGO");
		    	Long pc = rs.getLong("PRECO");
		    	produto.setId(id);
		    	produto.setNome(nome);
		    	produto.setCodigo(cd);
		    	produto.setPreco(pc);
		    	list.add(produto);
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
	public Integer atualizar(Produto produto) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "update tb_produto set codigo = ?, nome = ?, preco = ? where ID = ?";
			stm = connection.prepareStatement(sql);
			stm.setString(1, produto.getCodigo());
			stm.setString(2, produto.getNome());
			stm.setLong(3, produto.getPreco());
			stm.setLong(4, produto.getId());
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
