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
import jakarta.ws.rs.QueryParam;
import uce.edu.web.api.matricula.application.EstudianteService;
import uce.edu.web.api.matricula.domain.Estudiante;

@Path("/estudiantes")
public class EstudianteResource {
    @Inject
    private EstudianteService estudianteService;

    @GET
    @Path("/todos")
    public List<Estudiante> listarTodos() {
        return this.estudianteService.listarTodos();
    }

    @GET
    @Path("consultarPorId/{id}") // para enviar variables se hace cob {}
    public Estudiante consultarPorId(@PathParam("id") Integer iden) {
        return this.estudianteService.consultarPorId(iden);
    }

    @POST
    @Path("/crear")
    public void guardarEstudiante(Estudiante estudiante) {
        this.estudianteService.crearEstudiante(estudiante);
    }

    @PUT
    @Path("/actualizar/{id}")
    public void actualizarEstudiante(@PathParam("id") Integer id, Estudiante estudiante) {
        this.estudianteService.actualizar(id, estudiante);
    }

    @PATCH
    @Path("/actualizarParcial/{id}")
    public void actualizarParcialEstudiante(@PathParam("id") Integer id, Estudiante estudiante) {
        this.estudianteService.actualizarParcial(id, estudiante);
    }

    @DELETE
    @Path("/borrar/{id}")
    public void borrarEstudiante(@PathParam("id") Integer id) {
        this.estudianteService.eliminar(id);
    }

    @GET
    @Path("buscarPorProvincia") // Eliminamos las llaves de aquí
    public List<Estudiante> buscarPorProvincia(
            @QueryParam("provincia") String provincia,
            @QueryParam("genero") String genero) {

        return this.estudianteService.buscarPorProvincia(provincia, genero);
    }
}
