package com.example.api_spring.api.controllers;

import com.example.api_spring.api.services.UsuarioService;
import com.example.api_spring.api.models.ApiResponseAthleta;
import com.example.api_spring.api.models.Usuario;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/adicionar")
    public ResponseEntity<ApiResponseAthleta> adicionarUsuario(@Valid @RequestBody Usuario usuario) {
        try {
            ApiResponseAthleta savedUserRole = usuarioService.cadastrarUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUserRole);
      } catch (DataIntegrityViolationException dive) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body( new ApiResponseAthleta(false, "Error", null, null));
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<ApiResponseAthleta> listarUsuarios(){
        try {
            ApiResponseAthleta response = usuarioService.listarUsuarios();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponseAthleta(false,"Não foi possível listar os usuários", null,null)
            );
        }
    }

    @GetMapping("/listar/{username}")
    public ResponseEntity<ApiResponseAthleta> listarUsuarioPorId(@PathVariable String username){
        try{
            ApiResponseAthleta response = usuarioService.findByUsernameResponse(username);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (DataIntegrityViolationException dive) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseAthleta(false, "Error", null, null));
        }
    }

//    @PostMapping("/adicionarProcedure")
//    public ResponseEntity<ApiResponse> adicionarUsuarioProcedure(@Valid @RequestBody UsuarioRequest usuarioRequest, BindingResult bindingResult) {
//        ApiResponse response = new ApiResponse();
//        if (bindingResult.hasErrors()) {
//            StringBuilder errorMessage = new StringBuilder("Erros de validação: ");
//            bindingResult.getFieldErrors().forEach(error -> {
//                errorMessage.append(error.getField())
//                        .append(" ")
//                        .append(error.getDefaultMessage())
//                        .append("; ");
//            });
//            response.setDescription(errorMessage.toString());
//            response.setResponseSucessfull(false);
//            return ResponseEntity.badRequest().body(response);
//        }
//        response = usuarioService.cadastrarUsuarioProcedure(usuarioRequest.getUsuario(), usuarioRequest.getUsuarioInteresse());
//        if (response.isResponseSucessfull()) {
//            return ResponseEntity.ok(response);
//        } else {
//            return ResponseEntity.badRequest().body(response);
//        }
//    }


    @PatchMapping("/mudarSenha/{email}/{novaSenha}")
    public ResponseEntity<ApiResponseAthleta> mudarSenha(@PathVariable String email, @PathVariable String novaSenha){
        ApiResponseAthleta response = usuarioService.mudarSenha(email, novaSenha);
        if (response.isResponseSucessfull()){
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponseAthleta(false,"Não foi possível alterar a senha",null,null)
            );
        }
    }

}