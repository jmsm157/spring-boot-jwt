package com.bolsadeideas.springboot.app.service;

import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.app.models.dao.IClienteDao;
import com.bolsadeideas.springboot.app.models.dao.IFacturaDao;
import com.bolsadeideas.springboot.app.models.dao.IProductoDao;
import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.models.entity.Factura;
import com.bolsadeideas.springboot.app.models.entity.Producto;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteDao clienteDao;

	@Autowired
	private IProductoDao productoDao;

	@Autowired
	private IFacturaDao facturaDao;

	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteDao.findAll();
	}

	@Transactional(readOnly = true)
	public Cliente findOne(Long id) {
		return clienteDao.findById(id).orElse(null);
	}

	@Transactional(readOnly = true)
	public Cliente fetchByIdWhithFacturas(Long id) {
		return clienteDao.fetchByIdWhithFacturas(id);
	}
	
	@Transactional
	public void save(Cliente cliente) {
		clienteDao.save(cliente);

	}

	@Transactional
	public void eliminar(Long id) {
		clienteDao.deleteById(id);
	}

	@Transactional(readOnly = true)
	public Page<Cliente> findAll(Pageable page) {
		return clienteDao.findAll(page);
	}

	@Transactional(readOnly = true)
	public List<Producto> findByNombre(String term) {
		return productoDao.findByNombreLikeIgnoreCase("%" + term + "%");
	}

	@Transactional
	public void saveFactura(Factura factura) {
		facturaDao.save(factura);
	}

	@Transactional(readOnly = true)
	public Producto findProductoById(Long id) {
		return productoDao.findById(id).orElse(null);
	}

	@Transactional(readOnly = true)
	public Factura findFacturaById(Long id) {
		return facturaDao.findById(id).orElse(null);
	}

	@Transactional
	public void deleteFactura(Long id) {
		facturaDao.deleteById(id);
	}

	@Transactional(readOnly = true)
	public Factura fetchByIDWithClienteWithItemFacturaWhithProducto(Long id) {
		return facturaDao.fetchByIDWithClienteWithItemFacturaWhithProducto(id);
	}
}
