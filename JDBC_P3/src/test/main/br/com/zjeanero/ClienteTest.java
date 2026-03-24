package br.com.zjeanero;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import br.com.zjeanero.dao.jdbc.dao.ClienteDAO;
import br.com.zjeanero.dao.jdbc.dao.IClienteDAO;
import br.com.zjeanero.domain.Cliente;

public class ClienteTest {
	
	@Test
	public void cadastrarTest() throws Exception {
		IClienteDAO dao = new ClienteDAO();
		
		Cliente cliente = new Cliente();
		cliente.setCodigo("01");
		cliente.setNome("Jean Secco");
		
		Integer qtd = dao.cadastrar(cliente);
		assertTrue(qtd == 1);
		
		Cliente clienteBD = dao.consultar(cliente.getCodigo());
		assertNotNull(clienteBD);
		assertNotNull(clienteBD.getId());
		assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
		assertEquals(cliente.getNome(), clienteBD.getNome());
		
		Integer qtdDel = dao.excluir(clienteBD);
		assertNotNull(qtdDel);
		
	}
	
	@Test
	public void buscarTodosTest() throws Exception {
		IClienteDAO dao = new ClienteDAO();		
		
		Cliente cliente = new Cliente();
		cliente.setCodigo("01");
		cliente.setNome("Jean Secco");
		Integer cadastro01 = dao.cadastrar(cliente);
		assertTrue(cadastro01 == 1);
		
		Cliente clientes = new Cliente();
		clientes.setCodigo("02");
		clientes.setNome("Jean Teste");
		Integer cadastro02 = dao.cadastrar(clientes);
		assertTrue(cadastro02 == 1);
		
		List<Cliente> list = dao.buscarTodos();
		assertNotNull(list);
		assertEquals(2, list.size());
		
		int cadastroDel = 0;
		for(Cliente cli : list) {
			dao.excluir(cli);
			cadastroDel++;
		}
		assertEquals(list.size(), cadastroDel);
		
		list = dao.buscarTodos();
		assertEquals(list.size(), 0);
	}
	
	@Test
	public void atualizarTest() throws Exception {
		IClienteDAO dao = new ClienteDAO();	
		
		Cliente cliente = new Cliente();
		cliente.setCodigo("01");
		cliente.setNome("Jean Secco");
		Integer cadastro01 = dao.cadastrar(cliente);
		assertTrue(cadastro01 == 1);
		
		Cliente clienteBD = dao.consultar("01");
		assertNotNull(clienteBD);
		assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
		assertEquals(cliente.getNome(), clienteBD.getNome());
		
		clienteBD.setCodigo("02");
		clienteBD.setNome("Jean Teste");
		Integer cadastro02 = dao.atualizar(clienteBD);
		assertTrue(cadastro02 == 1);
		
		Cliente clienteBD01 = dao.consultar("01");
		assertNull(clienteBD01);
		
		Cliente clienteBD02 = dao.consultar("02");
		assertNotNull(clienteBD02);
		assertEquals(clienteBD.getId(), clienteBD02.getId());
		assertEquals(clienteBD.getCodigo(), clienteBD02.getCodigo());
		assertEquals(clienteBD.getNome(), clienteBD02.getNome());
		
		List<Cliente> list = dao.buscarTodos();
		for (Cliente cli : list) {
			dao.excluir(cli);
		}
	}
}
