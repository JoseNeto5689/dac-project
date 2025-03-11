package com.example.bilheteriaservice.consumer;

import com.example.bilheteriaservice.dto.TicketRequestDTO;
import com.example.bilheteriaservice.services.TicketService;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class TicketRequestConsumer {

    private final TicketService ticketService;

    private final Gson gson = new Gson();

    public TicketRequestConsumer(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @RabbitListener(queues = {"ticket-request-queue"})
    public void receive(@Payload String dto) {
        try {
            TicketRequestDTO ticket = gson.fromJson(dto, TicketRequestDTO.class);
            ticketService.save(ticket);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
