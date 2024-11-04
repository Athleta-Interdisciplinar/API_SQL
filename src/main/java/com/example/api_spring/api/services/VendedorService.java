package com.example.api_spring.api.services;

import com.example.api_spring.api.models.ApiResponseAthleta;
import com.example.api_spring.api.models.Usuario;
import com.example.api_spring.api.models.Vendedor;
import com.example.api_spring.api.repositories.UsuarioRepository;
import com.example.api_spring.api.repositories.VendedorRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VendedorService {
    private final VendedorRepository vendedorRepository;
    public VendedorService(VendedorRepository vendedorRepository){
        this.vendedorRepository = vendedorRepository;
    }

    public ApiResponseAthleta cadastrarVendedor(Vendedor vendedor){
        try{
            vendedor.setIdUsuario(vendedor.getIdUsuario());
            vendedor.setCpf(vendedor.getCpf());
            vendedor.setEndereco(vendedor.getEndereco().strip().toLowerCase());
            vendedor.setCep(vendedor.getCep());
            vendedor.setNumero(vendedor.getNumero());
            vendedor.setFotoPerfil(vendedor.getFotoPerfil());
            vendedor.setTelefone(vendedor.getTelefone());

            Vendedor vendedorResponse =  vendedorRepository.save(vendedor);
            List<Object> vendedorList = new ArrayList<>();
            vendedorList.add(vendedorResponse);
            return new ApiResponseAthleta(true, "Vendedor cadastrado com sucesso", vendedorList, null);
        } catch (Exception e){
            return new ApiResponseAthleta(false, "Não foi possível cadastrar o vendedor", null, null);
        }
    }

    public ApiResponseAthleta findById(Long id){
        try {
            Vendedor response = vendedorRepository.findVendedorByIdUsuario(id);
            if(response != null){
                List<Object> vendedorList = new ArrayList<>();
                vendedorList.add(response);
                return new ApiResponseAthleta(true, "Vendedor pego com sucesso", vendedorList, null);
            }
            return new ApiResponseAthleta(false, "Vendedor não existe no banco", null, null);
        } catch (Exception e){
            return new ApiResponseAthleta(false, "Não foi possível pegar o vendedor", null, null);
        }
    }
}
