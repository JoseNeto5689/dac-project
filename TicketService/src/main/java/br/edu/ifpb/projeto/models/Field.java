package br.edu.ifpb.projeto.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
public class Field implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String type;

    private String description;

    @JsonIgnore
    @ManyToOne
    private Modality modality;

    @JsonIgnore
    @OneToMany(mappedBy = "field")
    private List<FieldResponse> fieldResponses;

}
