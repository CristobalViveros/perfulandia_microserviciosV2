package Pago.demo.modelo;

import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "trasaciones")
public class TransaccionDTO {
     private String numeroTarjeta;
    private String titular;
    private String fechaExpiracion;
    private String cvv;
   
   

}
