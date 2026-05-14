package Pago.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import Pago.demo.modelo.PagoModelo;
import Pago.demo.modelo.PagoRequestDTO;
import Pago.demo.service.PagoService;


@RestController
@RequestMapping("/pagos")
public class PagoController {
        @Autowired
    private PagoService pagoService;

    @PostMapping
    public PagoModelo procesarPago(@RequestBody PagoRequestDTO pagoDTO) {
    return pagoService.procesarPago(pagoDTO);
}
    
}
