package uce.edu.web.api.matricula.application;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.api.matricula.domain.Hijo;
import uce.edu.web.api.matricula.infraestructure.HijoRepository;

@ApplicationScoped
public class HijoService {
    @Inject
    private HijoRepository hijoRepository;

    public List<Hijo> buscarPorIdEstudiante(Integer estudianteId) {
        return this.hijoRepository.buscarPorIdEstudiante(estudianteId);
    }
    

}
