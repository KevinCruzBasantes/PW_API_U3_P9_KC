package uce.edu.web.api.matricula.application;

import java.util.List;

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
        List<EstudianteRepresentation> list = new ArrayList<>();

        return this.estudianteRepository.listAll();
    }
    public EstudianteRepresentation consultarPorId(Integer id){
        return this.mapperToER(this.estudianteRepository.findById(id.longValue()));
    }
    @Transactional
    public void crearEstudiante(Estudiante estudiante){
        this.estudianteRepository.persist(estudiante);
    }
    @Transactional
    public void actualizar(Integer id, Estudiante estudiante){
        Estudiante est = this.mapperToEstudiante(this.consultarPorId(id));
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
    public List<Estudiante> buscarPorProvincia(String provincia , String genero){
        //return this.estudianteRepository.find("provincia", provincia).list();
        return this.estudianteRepository.find("provincia = ?1 and genero = ?2", provincia, genero).list();
    }
    //
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

     private EstudianteRepresentation mapperToEstudiante(EstudianteRepresentation estudiante){
        Estudiante estR = new Estudiante();
        estR.setId(estudiante.getId().intValue());
        estR.setNombre(estudiante.getNombre());
        estR.setApellido(estudiante.getApellido());
        estR.setFechaNacimiento(estudiante.getFechaNacimiento());
        estR.setProvincia(estudiante.getProvincia());
        estR.setGenero(estudiante.getGenero());
        return estR;
    }
}
