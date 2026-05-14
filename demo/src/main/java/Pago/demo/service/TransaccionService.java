package Pago.demo.service;

import org.springframework.stereotype.Service;
import Pago.demo.modelo.TransaccionDTO;


@Service
public interface TransaccionService {
    TransaccionDTO obtenerTransaccionPorPagoId(Long pagoId);
}
