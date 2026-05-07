package Pago.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Pago.demo.modelo.PagoModelo;
import Pago.demo.modelo.PagoRequestDTO;
import Pago.demo.modelo.TransaccionDTO;
import Pago.demo.repository.PagoRepository;
@Service
public class PagoServiceImpl  implements PagoService {
    @Autowired
    private PagoRepository pagoRepository;

    @Override
    public PagoModelo procesarPago(PagoRequestDTO pagoDTO, TransaccionDTO transaccionDTO) {

        PagoModelo pago = new PagoModelo();
        pago.setPedidoId(pagoDTO.getPedidoId());
        pago.setMonto(pagoDTO.getMonto());

        // VALIDACIÓN SIMPLE (simulación)
        if (transaccionDTO.getNumeroTarjeta() != null) {
            pago.setEstado("APROBADO");
        } else {
            pago.setEstado("RECHAZADO");
        }

        return pagoRepository.save(pago);
    }

}
