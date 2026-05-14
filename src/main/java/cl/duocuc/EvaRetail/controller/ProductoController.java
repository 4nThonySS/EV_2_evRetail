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

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    //istar
    @GetMapping
    public ResponseEntity<List<ProductoResponse>> listarProductos() {

        return ResponseEntity.ok(productoService.listarProductos());
    }


    //guardar
    @PostMapping
    public ResponseEntity<ProductoResponse> guardarProducto(
            @Valid @RequestBody ProductoRequest request) {

        ProductoResponse response = productoService.guardarProducto(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //buscar x id
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponse> obtenerProductoPorID(
            @PathVariable Long id){

        return ResponseEntity.ok(productoService.obtenerProductoPorID(id));
    }

    //actualizar x id
    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponse> actualizarProducto(
            @PathVariable Long id,
            @Valid @RequestBody ProductoRequest request){

        return ResponseEntity.ok(productoService.actualizarProducto(id, request));
    }


    //eliminar x id

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> eliminarProducto(@PathVariable Long id){

        productoService.eliminarProducto(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Producto eliminado correctamente",
                        false,
                        null
                )
        );
    }

    @PutMapping("/{id}/reducir-stock")
    public ResponseEntity<ProductoResponse> reducirStock(
            @PathVariable Long id,
            @RequestParam Integer cantidad){

        return ResponseEntity.ok(
                productoService.reducirStock(id, cantidad)
        );
    }

}
