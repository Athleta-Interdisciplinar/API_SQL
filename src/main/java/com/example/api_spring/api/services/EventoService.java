package com.example.api_spring.api.services;

import com.example.api_spring.api.models.ApiResponseAthleta;
import com.example.api_spring.api.models.Evento;
import com.example.api_spring.api.repositories.EventoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;

    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public ApiResponseAthleta listarEventos(){
        try{
            List<Evento> eventoList = eventoRepository.findAll();
            if (!eventoList.isEmpty()){
                List<Object> listaObjetos = eventoList.stream()
                        .map(evento -> (Object) evento)
                        .toList();
                return new ApiResponseAthleta(true, "Eventos retornados com sucesso!", listaObjetos, null);
            }
            return new ApiResponseAthleta(false, "Não há eventos no banco", null, "Vazio");
        } catch (Exception e){
            return new ApiResponseAthleta(false, "Falha ao retornar eventos", null, null);
        }
    }

    public ApiResponseAthleta listarEventoPorNome(String nome){
        try{
            List<Evento> eventoList = eventoRepository.findAllByNome(nome);
            if (!eventoList.isEmpty()){
                List<Object> listaObjetos = eventoList.stream()
                        .map(evento -> (Object) evento)
                        .toList();
                return new ApiResponseAthleta(true, "Eventos retornados com sucesso!", listaObjetos, null);
            }
            return new ApiResponseAthleta(false, "Não há eventos com este nome no banco", null, "Vazio");
        } catch (Exception e){
            return new ApiResponseAthleta(false, "Falha ao retornar eventos", null, null);
        }
    }

    public ApiResponseAthleta inserirEvento(Evento evento){
        try{
            Evento eventoResponse = eventoRepository.save(evento);
            List<Object> eventoList = new ArrayList<>();
            eventoList.add(eventoResponse);
            return new ApiResponseAthleta(true, "Evento inserido com sucesso", eventoList, null);
        }catch (Exception e){
            return new ApiResponseAthleta(false, "Não foi possível inserir o evento", null, null);
        }
    }

    public ApiResponseAthleta excluirEvento(Long id){
        try{
            eventoRepository.deleteEventoByIdEvento(id);
            return new ApiResponseAthleta(true, "Evento excluido com sucesso", null, null);
        }catch (Exception e){
            return new ApiResponseAthleta(false, "Não foi possível excluir o evento", null, null);
        }
    }


}