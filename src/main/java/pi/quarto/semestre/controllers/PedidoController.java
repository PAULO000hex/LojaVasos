package pi.quarto.semestre.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import pi.quarto.semestre.models.Pedido;
import pi.quarto.semestre.models.Produto;
import pi.quarto.semestre.repositories.PedidoRepository;
import pi.quarto.semestre.repositories.ProdutoImagensRepositorio;

import java.util.Optional;
@Controller
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepo;

    @GetMapping("/backoffice/pedido/alterarStatus/{id}")
    public String alterarStatus(@PathVariable("id") long id, @RequestParam String status) {
        Optional<Pedido> pedido = pedidoRepo.findById(id);

        if (pedido==null) {
            throw new IllegalArgumentException("Produto inválido!");
        }

        Pedido pedidoBanco = pedido.get();
        pedidoBanco.setStatus(status);
        pedidoRepo.save(pedidoBanco);

        return "redirect:/backoffice/listarPedidos";
    }
}
