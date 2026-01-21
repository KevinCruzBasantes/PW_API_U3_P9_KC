package uce.edu.web.api.matricula.application;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.domain.Materia;
import uce.edu.web.api.matricula.infraestructure.MateriaRepository;
import uce.edu.web.api.matricula.infraestructure.MateriaResumenDTO;

@ApplicationScoped
public class MateriaService {
    @Inject
    private MateriaRepository materiaRepository;

    public List<Materia> listarTodos() {
        return this.materiaRepository.listAll();
    }

    public Materia consultarPorId(Integer id) {
        return this.materiaRepository.findById(id.longValue());
    }

    @Transactional
    public void crearMateria(Materia materia) {
        this.materiaRepository.persist(materia);
    }

    @Transactional
    public void actualizar(Integer id, Materia materia) {
        Materia mat = this.consultarPorId(id);
        mat.setNombre(materia.getNombre());
        mat.setDescripcion(materia.getDescripcion());
        mat.setCreditos(materia.getCreditos());
        mat.setCodigoAcademico(materia.getCodigoAcademico());
        mat.setHorasSemanales(materia.getHorasSemanales());
        mat.setEstado(materia.getEstado());

        // se actualiza automaticamente por dirty checking
    }

    @Transactional
    public void actualizarParcial(Integer id, Materia materia) {
        Materia mat = this.consultarPorId(id);
        if (materia.getNombre() != null) {
            mat.setNombre(materia.getNombre());
        }
        if (materia.getDescripcion() != null) {
            mat.setDescripcion(materia.getDescripcion());
        }
        if (materia.getCreditos() != null) {
            mat.setCreditos(materia.getCreditos());
        }
        if (materia.getCodigoAcademico() != null) {
            mat.setCodigoAcademico(materia.getCodigoAcademico());
        }
        if (materia.getHorasSemanales() != null) {
            mat.setHorasSemanales(materia.getHorasSemanales());
        }
        if (materia.getEstado() != null) {
            mat.setEstado(materia.getEstado());
        }
        // se actualiza automaticamente por dirty checking
        

    }

    @Transactional
    public void eliminar(Integer id) {
        this.materiaRepository.deleteById(id.longValue());
    }

    public List<Materia> buscarMateriaPorRango(Integer min, Integer max) {
        Boolean estadoActivo = true;
        return materiaRepository.buscarPorCreditoyEstado(min, max, estadoActivo);

    }

    public List<MateriaResumenDTO> listarResumen() {
        return materiaRepository.listAll()
                .stream()
                .map(m -> new MateriaResumenDTO(
                        m.getId(),
                        m.getNombre(),
                        m.getCreditos()))
                .toList();
    }

    @Transactional
    public int desactivarMaterias(Integer maxCreditos) {
        return materiaRepository.desactivarPorCreditos(maxCreditos);
    }
}
