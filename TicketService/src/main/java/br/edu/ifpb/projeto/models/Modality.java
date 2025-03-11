package br.edu.ifpb.projeto.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Modality implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String type;

    @JsonIgnore
    @ManyToOne
    private Event event;

    @ToString.Exclude
    @OneToMany(mappedBy = "modality", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Field> fields;

}