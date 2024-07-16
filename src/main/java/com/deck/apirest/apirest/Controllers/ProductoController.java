package com.deck.apirest.apirest.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.deck.apirest.apirest.Repositories.ProductoRepository;
import com.deck.apirest.apirest.Entities.Producto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/productos")
public class ProductoController {

@Autowired
private ProductoRepository productoRepository;

@GetMapping
public List<Producto> getAllProducto(){
return productoRepository.findAll();
}

@GetMapping("/{id}")
    public Producto getProductobyID(@PathVariable Long id){
    return productoRepository.findById(id)
    .orElseThrow(() -> new RuntimeException("No se encontro el producto con el ID: "+ id));
}

@PostMapping
public Producto createProducto(@RequestBody Producto producto){
    return productoRepository.save(producto);
}

@PutMapping("/{id}")
public Producto updateProducto(@PathVariable Long id , @RequestBody Producto productoDetails){
    Producto producto = productoRepository.findById(id)
    .orElseThrow(() -> new RuntimeException("No se encontro el producto con el ID: "+ id));

    producto.setNombre(productoDetails.getNombre());
    producto.setPrecio(productoDetails.getPrecio());

    return productoRepository.save(producto);

}

@DeleteMapping("/{id}")
public String deleteProducto(@PathVariable Long id ){
    Producto producto = productoRepository.findById(id)
    .orElseThrow(() -> new RuntimeException("No se encontro el producto con el ID: "+ id));

    productoRepository.delete(producto);

    return "El producto con ID " + id + " fue eliminado correctamente";
}


}
