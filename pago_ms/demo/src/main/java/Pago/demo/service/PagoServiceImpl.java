package Pago.demo.service;
import java.time.LocalDateTime;
import java.util.List;
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
    private TransaccionService transaccionService;

        public PagoServiceImpl(PagoRepository pagoRepository, TransaccionService transaccionService) {
        this.pagoRepository = pagoRepository;
        this.transaccionService = transaccionService;
    }

         @Override
         public PagoModelo procesarPago(PagoRequestDTO pagoDTO, TransaccionDTO transaccionDTO) {

        if (Float.parseFloat(pagoDTO.getMonto()+"") <= 0) {
            throw new RuntimeException("Monto inválido");
        }

        if (pagoDTO.getPedidoId() == null) {
            throw new RuntimeException("PedidoId requerido");
        }

        PagoModelo pago = new PagoModelo();
        pago.setPedidoId(pagoDTO.getPedidoId());
        pago.setMonto(pagoDTO.getMonto());
        pago.setFecha(LocalDateTime.now());

        if (transaccionDTO.getNumeroTarjeta() == null ||
            transaccionDTO.getNumeroTarjeta().length() < 8) {
            pago.setEstado("RECHAZADO");
        } else {
            pago.setEstado("APROBADO");
        }

        return pagoRepository.save(pago);
        }

        @Override
        public PagoModelo obtenerPagoPorId(Long id) {
            return pagoRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Pago no encontrado"));
        }

        @Override
        public List<PagoModelo> listarPagos() {
            return pagoRepository.findAll();
        }

        @Override
        public void eliminarPago(Long id) {
            if (!pagoRepository.existsById(id)) {
                throw new RuntimeException("Pago no existe");
            }
            pagoRepository.deleteById(id);
        }

        @Override
        public PagoModelo procesarPago(PagoModelo pagoModelo) {
           if (pagoModelo == null) {
        throw new IllegalArgumentException("PagoModelo no puede ser null");
          }
        
        
        TransaccionDTO trans = transaccionService.obtenerTransaccionPorPagoId(pagoModelo.getId());
        if (trans == null) {
            throw new IllegalStateException("No hay Transaccion disponible para el pago id=" + pagoModelo.getId());
        }

        PagoRequestDTO req = new PagoRequestDTO(pagoModelo.getPedidoId(), pagoModelo.getMonto());
        return procesarPago(req, trans);
            }

        @Override
        public PagoModelo procesarPago(PagoRequestDTO pagoDTO) {       
            if (pagoDTO == null) {
                throw new IllegalArgumentException("PagoRequestDTO no puede ser null");
            }
            TransaccionDTO trans = transaccionService.obtenerTransaccionPorPagoId(pagoDTO.getPedidoId());
            if (trans == null) {
                throw new IllegalStateException("No hay Transaccion disponible para el pedido id=" + pagoDTO.getPedidoId());
            }
            PagoModelo pago = new PagoModelo();
            pago.setPedidoId(pagoDTO.getPedidoId());
            pago.setMonto(pagoDTO.getMonto());
            return pagoRepository.save(pago);

        } 

}
