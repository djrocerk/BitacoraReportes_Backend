package com.reportesclaro.Entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ApisWavenet")
public class ApisWavenetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column
    private Long id;

    @Column(name = "name_ApisWavenet", length = 300)
    private String name_ApisWavenet;

    @ManyToOne // Muchas apis de wavenet consumen un solo microservicio de claro
    @JoinColumn(name = "id_MicroservicesClaro")
    private MicroservicesClaroEntity microservicesClaro;
}
//(optional = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)