package uce.edu.web.api.matricula.application;

import java.util.ArrayList;
import java.util.List;

import com.arjuna.ats.internal.jdbc.drivers.modifiers.list;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.api.matricula.application.representation.HijoRepresentation;
import uce.edu.web.api.matricula.domain.Hijo;
import uce.edu.web.api.matricula.infraestructure.HijoRepository;

@ApplicationScoped
public class HijoService {
    @Inject
    private HijoRepository hijoRepository;

    public List<HijoRepresentation> buscarPorIdEstudiante(Integer estudianteId) {
        List<HijoRepresentation > lista =new ArrayList<>();
        for(Hijo h: this.hijoRepository.buscarPorIdEstudiante(estudianteId)){
            lista.add(this.mapperToHijoR(h));
        }
        return lista;
    }

    private HijoRepresentation mapperToHijoR(Hijo hijo){
        HijoRepresentation hr = new HijoRepresentation();
        hr.setId(hijo.getId().intValue());
        hr.setNombre(hijo.getNombre());
        hr.setApellido(hijo.getApellido());
        return hr;
    }

}
