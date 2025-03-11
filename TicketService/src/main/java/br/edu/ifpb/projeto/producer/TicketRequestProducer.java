package br.edu.ifpb.projeto.producer;

import br.edu.ifpb.projeto.dtos.EmaIlDTO;
import br.edu.ifpb.projeto.dtos.TicketRequestDTO;
import com.google.gson.Gson;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketRequestProducer {
    @Autowired
    private AmqpTemplate amqpTemplate;

    private final Gson gson = new Gson();

    public void integrate(TicketRequestDTO dto) {
        amqpTemplate.convertAndSend(
                "ticket-request-exchange",
                "ticket-request-rout-key",
                gson.toJson(dto)
        );
    }
}
