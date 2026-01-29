package uce.edu.web.api.matricula.application;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.application.representation.EstudianteRepresentation;
import uce.edu.web.api.matricula.domain.Estudiante;
import uce.edu.web.api.matricula.infraestructure.EstudianteRepository;

@ApplicationScoped
public class EstudianteService {
    @Inject
    private EstudianteRepository estudianteRepository;

    public List<EstudianteRepresentation> listarTodos(){
        return this.estudianteRepository.listAll()
            .stream()
            .map(this::mapperToER)
            .collect(Collectors.toList());
    }
    
    public EstudianteRepresentation consultarPorId(Integer id){
        return this.mapperToER(this.estudianteRepository.findById(id.longValue()));
    }
    
    @Transactional
    public void crearEstudiante(EstudianteRepresentation estudiante){
        this.estudianteRepository.persist(this.mapperToEstudiante(estudiante));
    }
    
    @Transactional
    public void actualizar(Integer id, EstudianteRepresentation estudiante){
        Estudiante est = this.estudianteRepository.findById(id.longValue());
        est.setNombre(estudiante.getNombre());
        est.setApellido(estudiante.getApellido());
        est.setFechaNacimiento(estudiante.getFechaNacimiento());
        est.setProvincia(estudiante.getProvincia());
        est.setGenero(estudiante.getGenero());
        //se actualiza automaticamente por dirty checking 
    }
    
    @Transactional
    public void actualizarParcial(Integer id, EstudianteRepresentation estudiante){
        Estudiante est = this.estudianteRepository.findById(id.longValue());
        if(estudiante.getNombre() != null){
            est.setNombre(estudiante.getNombre());
        }
        if(estudiante.getApellido() != null){
            est.setApellido(estudiante.getApellido());
        }
        if(estudiante.getFechaNacimiento() != null){
            est.setFechaNacimiento(estudiante.getFechaNacimiento());
        }
        if(estudiante.getProvincia() != null){
            est.setProvincia(estudiante.getProvincia());
        }
        if(estudiante.getGenero() != null){
            est.setGenero(estudiante.getGenero());
        }
    }
    
    @Transactional
    public void eliminar(Integer id){
        this.estudianteRepository.deleteById(id.longValue());
    }
    
    public List<EstudianteRepresentation> buscarPorProvincia(String provincia, String genero){
        return this.estudianteRepository.find("provincia = ?1 and genero = ?2", provincia, genero)
            .list()
            .stream()
            .map(this::mapperToER)
            .collect(Collectors.toList());
    }
    
    private EstudianteRepresentation mapperToER(Estudiante estudiante){
        EstudianteRepresentation rep = new EstudianteRepresentation();
        rep.setId(estudiante.getId().intValue());
        rep.setNombre(estudiante.getNombre());
        rep.setApellido(estudiante.getApellido());
        rep.setFechaNacimiento(estudiante.getFechaNacimiento());
        rep.setProvincia(estudiante.getProvincia());
        rep.setGenero(estudiante.getGenero());
        return rep;
    }

    private Estudiante mapperToEstudiante(EstudianteRepresentation estudiante){
        Estudiante rep = new Estudiante();
        if(estudiante.getId() != null){
            rep.setId((int) estudiante.getId().longValue());
        }
        rep.setNombre(estudiante.getNombre());
        rep.setApellido(estudiante.getApellido());
        rep.setFechaNacimiento(estudiante.getFechaNacimiento());
        rep.setProvincia(estudiante.getProvincia());
        rep.setGenero(estudiante.getGenero());
        return rep;
    }
}