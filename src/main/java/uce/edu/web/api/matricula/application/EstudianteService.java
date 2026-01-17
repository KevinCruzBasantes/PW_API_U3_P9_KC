package uce.edu.web.api.matricula.application;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.domain.Estudiante;
import uce.edu.web.api.matricula.infraestructure.EstudianteRepository;

@ApplicationScoped
public class EstudianteService {
    @Inject
    private EstudianteRepository estudianteRepository;

    public List<Estudiante> listarTodos(){
        return this.estudianteRepository.listAll();
    }
    public Estudiante consultarPorId(Integer id){
        return this.estudianteRepository.findById(id.longValue());
    }
    @Transactional
    public void crearEstudiante(Estudiante estudiante){
        this.estudianteRepository.persist(estudiante);
    }
    @Transactional
    public void actualizar(Integer id, Estudiante estudiante){
        Estudiante est = this.consultarPorId(id);
        est.setNombre(estudiante.getNombre());
        est.setApellido(estudiante.getApellido());
        est.setFechaNacimiento(estudiante.getFechaNacimiento());
        //se actualiza automaticamente por dirty checking 
    }
    @Transactional
    public void actualizarParcial(Integer id, Estudiante estudiante){
        Estudiante est = this.consultarPorId(id);
        if(estudiante.getNombre() != null){
            est.setNombre(estudiante.getNombre());
        }
        if(estudiante.getApellido() != null){
            est.setApellido(estudiante.getApellido());
        }
        if(estudiante.getFechaNacimiento() != null){
            est.setFechaNacimiento(estudiante.getFechaNacimiento());
        }

    }
    @Transactional
    public void eliminar(Integer id){
        this.estudianteRepository.deleteById(id.longValue());
    }

}
