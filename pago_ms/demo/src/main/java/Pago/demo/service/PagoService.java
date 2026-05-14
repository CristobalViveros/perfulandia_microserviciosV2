package Pago.demo.service;

import java.util.List;
import Pago.demo.modelo.PagoModelo;
import Pago.demo.modelo.PagoRequestDTO;
import Pago.demo.modelo.TransaccionDTO;


public interface PagoService {

   PagoModelo procesarPago(PagoRequestDTO pagoDTO);

   PagoModelo procesarPago(PagoModelo pagoDTO);

   PagoModelo obtenerPagoPorId(Long id);

   List<PagoModelo> listarPagos();

   void eliminarPago(Long id);

   PagoModelo procesarPago(PagoRequestDTO pagoDTO, TransaccionDTO transaccionDTO);
}
        


