package Pago.demo.modelo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransaccionDTO {
     private String numeroTarjeta;
    private String titular;
    private String fechaExpiracion;
    private String cvv;

}
