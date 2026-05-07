package Pago.demo.service;

import Pago.demo.modelo.PagoModelo;
import Pago.demo.modelo.PagoRequestDTO;
import Pago.demo.modelo.TransaccionDTO;

public interface PagoService {
        PagoModelo procesarPago(PagoRequestDTO pagoDTO, TransaccionDTO transaccionDTO);
}
