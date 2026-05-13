package cl.duocuc.EvaRetail.service;

import cl.duocuc.EvaRetail.dto.ProductoRequest;
import cl.duocuc.EvaRetail.dto.ProductoResponse;
import cl.duocuc.EvaRetail.model.Producto;
import cl.duocuc.EvaRetail.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductoService {

    private final ProductoRepository productoRepository;

    private ProductoResponse convertirAResponse(Producto producto){

        ProductoResponse response = new ProductoResponse();

        response.setId(producto.getId());
        response.setNombre(producto.getNombre());
        response.setPrecio(producto.getPrecio());
        response.setStock(producto.getStock());

        return response;
    }

    private Producto convertirAEntity(ProductoRequest request){

        Producto producto = new Producto();

        producto.setNombre(request.getNombre());
        producto.setPrecio(request.getPrecio());
        producto.setStock(request.getStock());

        return producto;
    }


    //Guardar
    public ProductoResponse guardarProducto(ProductoRequest request){

        Producto producto = convertirAEntity(request);

        Producto guardado = productoRepository.save(producto);

        return convertirAResponse(guardado);
    }

    //Listar
    @Transactional(readOnly = true)
    public List<ProductoResponse> listarProductos() {

        List<Producto> productos = productoRepository.findAll();

        List<ProductoResponse> respuesta = new ArrayList<>();

        for (Producto producto : productos) {
            ProductoResponse dto = convertirAResponse(producto);
            respuesta.add(dto);
        }

        return respuesta;
    }

    //buscar x id

    @Transactional(readOnly = true)
    public ProductoResponse obtenerProductoPorID (Long id) {

        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ERROR: Producto no encontrado"));

        return convertirAResponse(producto);
    }


    // actualizar x id

    public ProductoResponse actualizarProducto(Long idProducto, ProductoRequest request) {

        Producto productoExistente = productoRepository.findById(idProducto)
                .orElseThrow(() -> new RuntimeException("ERROR: Producto no encontrado"));

        productoExistente.setNombre(request.getNombre());
        productoExistente.setPrecio(request.getPrecio());
        productoExistente.setStock(request.getStock());

        Producto productoActualizado = productoRepository.save(productoExistente);

        return convertirAResponse(productoActualizado);
    }

    //eliminar xid
    public void eliminarProducto(Long id){

        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ERROR: Producto no encontrado"));

        productoRepository.delete(producto);
    }


}