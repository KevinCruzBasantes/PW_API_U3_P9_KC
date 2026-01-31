package uce.edu.web.api.matricula.interfaces;

import java.util.List;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
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
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.matricula.application.EstudianteService;
import uce.edu.web.api.matricula.application.HijoService;
import uce.edu.web.api.matricula.application.representation.EstudianteRepresentation;
import uce.edu.web.api.matricula.application.representation.HijoRepresentation;
import uce.edu.web.api.matricula.application.representation.LinkDTO;
import jakarta.ws.rs.Produces;

@Path("/estudiantes")
public class EstudianteResource {
    @Inject
    private EstudianteService estudianteService;
    @Inject
    private HijoService hijoService;
    @Context
    private UriInfo uriInfo;//mapea el puerto si se produce un cambio para que siga funcionando el link
    
    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin"})
    public List<EstudianteRepresentation> listarTodos() {
        System.out.println("Listar Todos XXXXXXXXXXXXXXX");
        return this.construirLinks(this.estudianteService.listarTodos());
    }

    @GET
    @Path("/provincia/genero")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin"})
    public List<EstudianteRepresentation> buscarPorProvincia(
            @QueryParam("provincia") String provincia,
            @QueryParam("genero") String genero) {

        System.out.println("Listar Todos Provincia y Genero XXXXXXXXXXXXXXX");
        return this.estudianteService.buscarPorProvincia(provincia, genero);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    //@PermitAll  //cualquiera
    @RolesAllowed({"admin"})
    public EstudianteRepresentation consultarPorId(@PathParam("id") Integer iden) {
        return this.construirLinks(this.estudianteService.consultarPorId(iden));
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin"})
    public Response guardarEstudiante(EstudianteRepresentation estudiante) {
        this.estudianteService.crearEstudiante(estudiante);
        return Response.status(Response.Status.CREATED).entity(estudiante).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin"})
    public Response actualizarEstudiante(@PathParam("id") Integer id, EstudianteRepresentation estudiante) {
        this.estudianteService.actualizar(id, estudiante);
        return Response.status(209).entity(estudiante).build();
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin"})
    public void actualizarParcialEstudiante(@PathParam("id") Integer id, EstudianteRepresentation estudiante) {
        this.estudianteService.actualizarParcial(id, estudiante);
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"admin"})
    public void borrarEstudiante(@PathParam("id") Integer id) {
        this.estudianteService.eliminar(id);
    }
    
    @GET
    @Path("/{id}/hijos")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin"})
    public List<HijoRepresentation> buscarPorIdEstudiante(@PathParam("id") Integer estudianteId) {
        return this.hijoService.buscarPorIdEstudiante(estudianteId);
    }

   private EstudianteRepresentation construirLinks(EstudianteRepresentation er){

    String self  = this.uriInfo.getBaseUriBuilder()
        .path(EstudianteResource.class)
        .path(String.valueOf(er.getId()))
        .build()
        .toString();

    String hijos = this.uriInfo.getBaseUriBuilder()
        .path(EstudianteResource.class)
        .path(String.valueOf(er.getId()))
        .path("hijos")
        .build(er.getId())
        .toString();

    // Agregar links usando getter
    er.setLinks(List.of(new LinkDTO(self, "self"), new LinkDTO(hijos, "hijos")));

    return er;
}
    private List<EstudianteRepresentation> construirLinks(List<EstudianteRepresentation> ers){
     for(EstudianteRepresentation er: ers){
          this.construirLinks(er);

     }
     return ers;

}
}