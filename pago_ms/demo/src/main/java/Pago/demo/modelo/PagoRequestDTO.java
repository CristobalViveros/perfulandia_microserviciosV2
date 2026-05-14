package Pago.demo.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PagoRequestDTO {
   
    private Long id;
    private BigDecimal monto;
    private Long pedidoId;
    private String estado;
    private LocalDateTime fecha;

    public PagoRequestDTO(Long pedidoId, BigDecimal monto) {
        this.pedidoId = pedidoId;
        this.monto = monto;
    }
}
