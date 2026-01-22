package uce.edu.web.api.matricula.interfaces;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import uce.edu.web.api.matricula.application.MateriaService;
import uce.edu.web.api.matricula.domain.Materia;
import uce.edu.web.api.matricula.infraestructure.MateriaResumenDTO;

@Path("/materias")
public class MateriaResource {
    @Inject
    private MateriaService materiaService;

    @GET
    @Path("")
    public List<Materia> listarTodos() {
        return this.materiaService.listarTodos();
    }

    @GET
    @Path("/{id}") // para enviar variables se hace cob {}
    public Materia consultarPorId(@PathParam("id") Integer iden) {
        return this.materiaService.consultarPorId(iden);
    }

    @POST
    @Path("")
    public void guardarMateria(Materia materia) {
        this.materiaService.crearMateria(materia);
    }

    @PUT
    @Path("/{id}")
    public void actualizarMateria(@PathParam("id") Integer id, Materia materia) {
        this.materiaService.actualizar(id, materia);
    }

    @PATCH
    @Path("/{id}")
    public void actualizarParcialMateria(@PathParam("id") Integer id, Materia materia) {
        this.materiaService.actualizarParcial(id, materia);
    }

    @DELETE
    @Path("/{id}")
    public void borrarMateria(@PathParam("id") Integer id) {
        this.materiaService.eliminar(id);
    }

    @GET
    @Path("/{min}/{max}")
    public List<Materia> buscar(@PathParam("min") Integer min, @PathParam("max") Integer max) {
        return materiaService.buscarMateriaPorRango(min, max);
    }

    @PUT
    @Path("/{creditos}")
    public String desactivar(@PathParam("creditos") Integer creditos) {
        int total = materiaService.desactivarMaterias(creditos);
        return "Materias desactivadas: " + total;
    }

    @GET
    @Path("/resumen")
    public List<MateriaResumenDTO> listarResumen() {
        return materiaService.listarResumen();
    }
}
