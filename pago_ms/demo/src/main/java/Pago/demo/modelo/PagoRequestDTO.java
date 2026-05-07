package Pago.demo.modelo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PagoRequestDTO {
    private Long pedidoId;
    private double monto;
}
