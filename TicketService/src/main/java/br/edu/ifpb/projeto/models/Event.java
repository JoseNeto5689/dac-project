package br.edu.ifpb.projeto.models;

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
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    private List<EventInfo> date;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Organizer> organizers;

}
