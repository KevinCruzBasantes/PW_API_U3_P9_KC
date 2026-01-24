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
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import uce.edu.web.api.matricula.application.MateriaService;
import uce.edu.web.api.matricula.domain.Materia;
import uce.edu.web.api.matricula.infraestructure.MateriaResumenDTO;

@Path("/materias")
@Produces(MediaType.APPLICATION_JSON)
public class MateriaResource {

    @Inject
    private MateriaService materiaService;

    // GET /materias
    @GET
    public Response listarTodos() {
        List<Materia> materias = materiaService.listarTodos();
        return Response.ok(materias).build(); // 200 OK
    }

    // GET /materias/{id}
    @GET
    @Path("/{id}")
    public Response consultarPorId(@PathParam("id") Integer id) {
        Materia materia = materiaService.consultarPorId(id);

        if (materia == null) {
            return Response.status(Response.Status.NOT_FOUND).build(); // 404
        }

        return Response.ok(materia).build(); // 200 OK
    }

    // POST /materias
    @POST
    public Response guardarMateria(Materia materia) {
        materiaService.crearMateria(materia);
        return Response.status(Response.Status.CREATED).build(); // 201 Created
    }

    // PUT /materias/{id}
    @PUT
    @Path("/{id}")
    public Response actualizarMateria(@PathParam("id") Integer id, Materia materia) {
        materiaService.actualizar(id, materia);
        return Response.ok(materia).build(); // 200 OK
        // alternativamente: Response.noContent().build(); // 204
    }

    // PATCH /materias/{id}
    @PATCH
    @Path("/{id}")
    public Response actualizarParcialMateria(@PathParam("id") Integer id, Materia materia) {
        materiaService.actualizarParcial(id, materia);
        return Response.noContent().build(); // 204 No Content
    }

    // DELETE /materias/{id}
    @DELETE
    @Path("/{id}")
    public Response borrarMateria(@PathParam("id") Integer id) {
        materiaService.eliminar(id);
        return Response.noContent().build(); // 204 No Content
    }

    // GET /materias?min=3&max=6
    @GET
    @Path("/buscar")
    public Response buscarPorRango(
            @QueryParam("min") Integer min,
            @QueryParam("max") Integer max) {

        List<Materia> materias = materiaService.buscarMateriaPorRango(min, max);
        return Response.ok(materias).build(); // 200 OK
    }

    // PATCH /materias/desactivar?creditos=5
    @PATCH
    @Path("/desactivar")
    public Response desactivarPorCreditos(@QueryParam("creditos") Integer creditos) {
        int total = materiaService.desactivarMaterias(creditos);
        return Response.ok("Materias desactivadas: " + total).build(); // 200 OK
    }

    // GET /materias/resumen
    @GET
    @Path("/resumen")
    public Response listarResumen() {
        List<MateriaResumenDTO> resumen = materiaService.listarResumen();
        return Response.ok(resumen).build(); // 200 OK
    }
}
