package br.edu.ifpb.projeto.producer;

import br.edu.ifpb.projeto.dtos.EmaIlDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailRequestProducer {
    @Autowired
    private AmqpTemplate amqpTemplate;

    private final Gson gson = new Gson();

    public void integrate(EmaIlDTO emaIlDTO) {
        amqpTemplate.convertAndSend(
                "email-request-exchange",
                "email-request-rout-key",
                gson.toJson(emaIlDTO)
        );
    }
}
