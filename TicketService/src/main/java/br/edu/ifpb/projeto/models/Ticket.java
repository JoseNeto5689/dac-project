package br.edu.ifpb.projeto.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Ticket implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private Event event;

    @ManyToOne(cascade = CascadeType.ALL)
    private Person owner;

    @ManyToOne
    private EventInfo eventDate;

    @OneToOne
    private Modality modality;

    @OneToMany(cascade = CascadeType.ALL)
    private List<FieldResponse> responseList;
}
