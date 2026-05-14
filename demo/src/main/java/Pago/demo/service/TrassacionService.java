package Pago.demo.service;

import org.springframework.stereotype.Service;
import Pago.demo.modelo.TransaccionDTO;


@Service
public interface TrassacionService {
    TransaccionDTO obtenerTransaccionPorPagoId(Long pagoId);
}
