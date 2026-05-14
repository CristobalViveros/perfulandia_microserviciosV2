package Pago.demo.repository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Pago.demo.modelo.PagoModelo;

@Repository

public interface PagoRepository  extends JpaRepository<PagoModelo, Long> {
    List<PagoModelo> findByPedidoId(Long pedidoId);

    List<PagoModelo> findByEstado(String estado);

    List<PagoModelo> findByMontoGreaterThan(Double monto);

    List<PagoModelo> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin);


}
