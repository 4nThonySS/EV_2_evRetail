package cl.duocuc.EvaRetail.controller;


import cl.duocuc.EvaRetail.dto.ApiResponse;
import cl.duocuc.EvaRetail.dto.ProductoRequest;
import cl.duocuc.EvaRetail.dto.ProductoResponse;
import cl.duocuc.EvaRetail.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;


import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    //istar
    @GetMapping
    public ResponseEntity<List<ProductoResponse>> listarProductos() {
        log.info("Recibida petición GET para listar productos");
        List<ProductoResponse> productos = productoService.listarProductos();
        log.info("Retornando {} productos", productos.size());

        return ResponseEntity.ok(productos);
    }


    //guardar
    @PostMapping
    public ResponseEntity<ProductoResponse> guardarProducto(
            @Valid @RequestBody ProductoRequest request) {

        log.info("Recibida petición POST para crear producto: {}", request.getNombre());
        ProductoResponse response = productoService.guardarProducto(request);

        log.info("Producto creado correctamente, retornando respuesta");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    //buscar x id
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponse> obtenerProductoPorID(@PathVariable Long id) {
        log.info("Recibida petición GET para obtener producto ID: {}", id);
        return ResponseEntity.ok(productoService.obtenerProductoPorID(id));
    }

    //actualizar x id
    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponse> actualizarProducto(
            @PathVariable Long id,
            @Valid @RequestBody ProductoRequest request) {

        log.info("Recibida petición PUT para actualizar producto ID: {}", id);
        return ResponseEntity.ok(productoService.actualizarProducto(id, request));
    }


    //eliminar x id


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> eliminarProducto(@PathVariable Long id) {
        log.info("Recibida petición DELETE para eliminar producto ID: {}", id);

        productoService.eliminarProducto(id);

        log.info("Producto eliminado correctamente");
        return ResponseEntity.ok(new ApiResponse<>(
                200,
                "Producto eliminado correctamente",
                false,
                null));
    }

    @PutMapping("/{id}/reducir-stock")
    public ResponseEntity<ProductoResponse> reducirStock(
            @PathVariable Long id,
            @RequestParam Integer cantidad) {

        log.info("Recibida petición para reducir stock - ID: {}, Cantidad: {}", id, cantidad);
        return ResponseEntity.ok(productoService.reducirStock(id, cantidad));
    }

}
