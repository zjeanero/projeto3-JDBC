package br.com.zjeanero;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import br.com.zjeanero.dao.jdbc.dao.ProdutoDAO;
import br.com.zjeanero.dao.jdbc.dao.ClienteDAO;
import br.com.zjeanero.dao.jdbc.dao.IClienteDAO;
import br.com.zjeanero.dao.jdbc.dao.IProdutoDAO;
import br.com.zjeanero.domain.Cliente;
import br.com.zjeanero.domain.Produto;

public class ProdutoTest {
	
	@Test
	public void cadastrarProdutoTest() throws Exception {
		IProdutoDAO dao = new ProdutoDAO();
		
		Produto produto = new Produto();
		produto.setCodigo("1");
		produto.setNome("Chocolate");
		produto.setPreco(10L);
		
		Integer qtd = dao.cadastrar(produto);
		assertTrue(qtd == 1);
		
		Produto produtoBD = dao.consultar(produto.getCodigo());
		assertNotNull(produtoBD);
		assertNotNull(produtoBD.getId());
		assertEquals(produto.getCodigo(), produtoBD.getCodigo());
		assertEquals(produto.getNome(), produtoBD.getNome());
		assertEquals(produto.getPreco(), produtoBD.getPreco());
		
		Integer qtdDel = dao.excluir(produtoBD);
		assertNotNull(qtdDel);
	}
	
	@Test
	public void buscarTodosTest() throws Exception {
		IProdutoDAO dao = new ProdutoDAO();		
		
		Produto produto = new Produto();
		produto.setCodigo("1");
		produto.setNome("Chocolate");
		produto.setPreco(10l);
		Integer produto01 = dao.cadastrar(produto);
		assertTrue(produto01 == 1);
		
		Produto produtos = new Produto();
		produtos.setCodigo("2");
		produtos.setNome("Chocolate Branco");
		produtos.setPreco(15l);
		Integer produto02 = dao.cadastrar(produtos);
		assertTrue(produto02 == 1);
		
		List<Produto> list = dao.buscarTodos();
		assertNotNull(list);
		assertEquals(2, list.size());
		
		int cadastroDelP = 0;
		for(Produto prod : list) {
			dao.excluir(prod);
			cadastroDelP++;
		}
		assertEquals(list.size(), cadastroDelP);
		
		list = dao.buscarTodos();
		assertEquals(list.size(), 0);
	}

	@Test
	public void atualizarTest() throws Exception {
		IProdutoDAO dao = new ProdutoDAO();	
		
		Produto produto = new Produto();
		produto.setCodigo("1");
		produto.setNome("Chocolate meio amargo");
		produto.setPreco(15l);
		Integer produto1 = dao.cadastrar(produto);
		assertTrue(produto1 == 1);
		
		Produto produtoBD = dao.consultar("1");
		assertNotNull(produtoBD);
		assertEquals(produto.getCodigo(), produtoBD.getCodigo());
		assertEquals(produto.getNome(), produtoBD.getNome());
		assertEquals(produto.getPreco(), produtoBD.getPreco());
		
		produtoBD.setCodigo("2");
		produtoBD.setNome("Chocolate teste");
		produto.setPreco(25l);
		Integer produto2 = dao.atualizar(produtoBD);
		assertTrue(produto2 == 1);
		
		Produto produtoBD1 = dao.consultar("1");
		assertNull(produtoBD1);
		
		Produto produtoBD2 = dao.consultar("2");
		assertNotNull(produtoBD2);
		assertEquals(produtoBD.getId(), produtoBD2.getId());
		assertEquals(produtoBD.getCodigo(), produtoBD2.getCodigo());
		assertEquals(produtoBD.getNome(), produtoBD2.getNome());
		assertEquals(produtoBD.getPreco(), produtoBD2.getPreco());
		
		List<Produto> list = dao.buscarTodos();
		for (Produto prod : list) {
			dao.excluir(prod);
		}
	}

}
