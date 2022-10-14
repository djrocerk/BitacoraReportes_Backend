package com.reportesclaro.Security.Entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "usuarios", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }),
        @UniqueConstraint(columnNames = { "email" }) })
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column
    private Long id;

    @Column
    private String username;

    @Column
    private String email;

    @Column
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "usuarios_Roles",
            joinColumns = @JoinColumn(name = "id_Usuarios", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_Roles", referencedColumnName = "id")
    )
    private Set<RoleEntity> roles;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_Persona")
    private PersonEntity onePerson;

    public void generatePassword(){
        String lowercase = "abcdefghijklmnopqrstuvwxyz";     //Minusculas
        String capitalletter ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";  //Mayusculas
        String digits ="0123456789";                         //Digitos
        String specials ="@#$%&?";                           //Cadenas especiales
        String passwordGenerate ="";                         //Contraseña Generada
        for(int i=0;i<2;i++){
            Random aleatorio= new Random();
            int posmin= aleatorio.nextInt(lowercase.length());
            int posmay=aleatorio.nextInt(capitalletter.length());
            int posDigitos= aleatorio.nextInt(digits.length());
            int posEspeciales= aleatorio.nextInt(specials.length());

            passwordGenerate+=lowercase.substring(posmin,posmin+1)+
                    capitalletter.substring(posmay,posmay+1)+
                    digits.substring(posDigitos,posDigitos+1)+
                    specials.substring(posEspeciales,posEspeciales+1);
        }

        this.password=passwordGenerate;
    }


}
