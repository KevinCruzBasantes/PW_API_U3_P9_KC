package uce.edu.web.api.matricula.interfaces;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.web.api.matricula.application.EstudianteService;
import uce.edu.web.api.matricula.application.HijoService;
import uce.edu.web.api.matricula.domain.Estudiante;
import uce.edu.web.api.matricula.domain.Hijo;
import jakarta.ws.rs.Produces;

@Path("/estudiantes")
public class EstudianteResource {
    @Inject
    private EstudianteService estudianteService;
    @Inject
    private HijoService hijoService;

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Estudiante> listarTodos() {
        System.out.println("Listar Todos XXXXXXXXXXXXXXX");
        return this.estudianteService.listarTodos();

    }

    @GET
    @Path("/provincia/genero") // Eliminamos las llaves de aquí
    @Produces(MediaType.APPLICATION_JSON)
    public List<Estudiante> buscarPorProvincia(
            @QueryParam("provincia") String provincia,
            @QueryParam("genero") String genero) {

        System.out.println("Listar Todos Provincia y Genero XXXXXXXXXXXXXXX");

        return this.estudianteService.buscarPorProvincia(provincia, genero);
    }

    @GET
    @Path("/{id}") // para enviar variables se hace cob {}
    @Produces(MediaType.APPLICATION_XML)
    public Estudiante consultarPorId(@PathParam("id") Integer iden) {
        return this.estudianteService.consultarPorId(iden);
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardarEstudiante(Estudiante estudiante) {
        this.estudianteService.crearEstudiante(estudiante);
        return Response.status(Response.Status.CREATED).entity(estudiante).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarEstudiante(@PathParam("id") Integer id, Estudiante estudiante) {
        this.estudianteService.actualizar(id, estudiante);
        return Response.status(209).entity(estudiante).build();
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void actualizarParcialEstudiante(@PathParam("id") Integer id, Estudiante estudiante) {
        this.estudianteService.actualizarParcial(id, estudiante);
    }

    @DELETE
    @Path("/{id}")
    public void borrarEstudiante(@PathParam("id") Integer id) {
        this.estudianteService.eliminar(id);
    }
    @GET
    @Path("/{id}/hijos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Hijo> buscarPorIdEstudiante(@PathParam("id") Integer estudianteId) {
        return this.hijoService.buscarPorIdEstudiante(estudianteId);
    }

}
