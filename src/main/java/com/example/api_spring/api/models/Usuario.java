package com.example.api_spring.api.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario")
public class Usuario {

    // TODO: Colocar o @generated para o campo se autocompletar, o @ deve ser colocado logo apos o @ id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Id único do usuário", example = "123")
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Size(max = 40, message = "Seu nome deve conter no maximo 40 caracteres")
    private String nome;

    @NotNull
    @Email
    @Size(max = 60, message = "Seu email deve conter no maximo 60 caracteres")
    private String email;

    @NotNull
    // TODO: Constraint
    @Size(max = 300, message = "Sua senha deve contar no máximo 20 caracteres")
    private String senha;

    @NotNull
    @Past(message = "A data de nascimento deve ser uma data passada")
    // TODO: Constraint
    @Column(name = "dt_nasc")
    private Date dtNasc;

    @NotNull
    @Size(max = 25, message = "Seu nome de usuário deve contar no máximo 25 caracteres")
    private String username;

    @Column(name = "foto_perfil")
    private String fotoPerfil;

    @Column(name = "user_role")
    private Long userRole;
}
