package uce.edu.web.api.matricula.infraestructure;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import uce.edu.web.api.matricula.domain.Materia;

@ApplicationScoped
public class MateriaRepository implements PanacheRepository<Materia> {
    

    public List<Materia> buscarPorCreditoyEstado(Integer min, Integer max, Boolean estado){
        return getEntityManager().createQuery("SELECT m FROM Materia m "
        + "WHERE m.creditos BETWEEN :min AND :max "
        + "AND m.estado = :estado " 
        + "ORDER BY m.nombre",Materia.class)
        .setParameter("min", min)
        .setParameter("max", max)
        .setParameter("estado", estado)
        .getResultList();
    }
    public int desactivarPorCreditos(Integer maxCreditos) {

        return getEntityManager()
            .createQuery("""
                UPDATE Materia m
                SET m.estado = false
                WHERE m.creditos < :max
            """)
            .setParameter("max", maxCreditos)
            .executeUpdate();
    }
}
