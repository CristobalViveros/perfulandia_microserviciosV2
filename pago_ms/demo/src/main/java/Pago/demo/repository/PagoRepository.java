package Pago.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Pago.demo.modelo.PagoModelo;

public interface PagoRepository  extends JpaRepository<PagoModelo, Long> {

}
