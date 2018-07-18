package edu.uade.seminario.tpo.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import edu.uade.seminario.tpo.dto.ItemIndicacionDTO;
import edu.uade.seminario.tpo.exception.BusinessException;
import edu.uade.seminario.tpo.model.EstadoIndicacion;
import edu.uade.seminario.tpo.model.Indicacion;
import edu.uade.seminario.tpo.model.Medicamento;
import edu.uade.seminario.tpo.model.Usuario;
import edu.uade.seminario.tpo.service.IndicacionService;
import edu.uade.seminario.tpo.service.UsuarioService;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Singleton
public class SistemaIndicaciones {

    @Inject
    private UsuarioService usuarioService;
    @Inject
    private IndicacionService indicacionService;

    @Inject
    private Gson gson;

    public Object loginUsuario(Request request, Response response) {
        JsonObject json = gson.fromJson(request.body(), JsonObject.class);
        String email = json.get("email").getAsString();
        String password = json.get("password").getAsString();

        if (email == null || email.isEmpty()) {
            response.status(HttpStatus.BAD_REQUEST_400);
            return "La dirección de correo es requerida";
        }
        if (password == null || password.isEmpty()) {
            response.status(HttpStatus.BAD_REQUEST_400);
            return "La contraseña es requerida";
        }
        try {
            Usuario u = usuarioService.login(email, password);
            response.status(HttpStatus.OK_200);
            return u;
        } catch (BusinessException be) {
            response.status(be.getStatus());
            return be.getMessage();
        }
    }

    public Object generarIndicacion(Request request, Response response) {
        JsonObject json = gson.fromJson(request.body(), JsonObject.class);
        String dniPaciente = json.get("dni").getAsString();
        String diagnostico = json.get("diag").getAsString();

        if (dniPaciente == null || dniPaciente.isEmpty()) {
            response.status(HttpStatus.BAD_REQUEST_400);
            return "El dni del paciente es requerido";
        }
        if (diagnostico == null || diagnostico.isEmpty()) {
            response.status(HttpStatus.BAD_REQUEST_400);
            return "El diagnostico es requerido";
        }

        try {
            Indicacion indicacion = indicacionService.generarIndicacion(dniPaciente, diagnostico);
            return indicacion.getCodigoIndicacion();
        } catch (BusinessException be) {
            response.status(be.getStatus());
            return be.getMessage();
        }
    }

    public Object agregarItemsIndicacion(Request request, Response response) {
        Long indicacionId = Long.parseLong(request.params("indicacionId"));
        JsonObject json = gson.fromJson(request.body(), JsonObject.class);
        JsonArray jsonArray = json.getAsJsonArray("items");
        if (jsonArray == null || jsonArray.size() == 0) {
            response.status(HttpStatus.BAD_REQUEST_400);
            return "Al menos un item es requerido";
        }
        List<ItemIndicacionDTO> items = new ArrayList<>();
        for (Iterator<JsonElement> it = jsonArray.iterator(); it.hasNext(); ) {
            JsonElement ele = it.next();
            ItemIndicacionDTO item = gson.fromJson(ele, ItemIndicacionDTO.class);
            items.add(item);
        }
        try {
            indicacionService.agregarItems(indicacionId, items);
            return "";
        } catch (BusinessException be) {
            response.status(be.getStatus());
            return be.getMessage();
        }
    }

    public Object finalizarCargaItems(Request request, Response response) {
        Long indicacionId = Long.parseLong(request.params("indicacionId"));
        String email = request.queryParams("email");
        if (email == null || email.isEmpty()) {
            response.status(HttpStatus.BAD_REQUEST_400);
            return "El parámetro email es requerido";
        }
        try {
            indicacionService.finalizarCargaItems(indicacionId, email);
            return "";
        } catch (BusinessException be) {
            response.status(be.getStatus());
            return be.getMessage();
        }
    }

    public Object buscarIndicacionesPorEstado(Request request, Response response) {
        String estado = request.queryParams("estado");
        if (estado == null || estado.isEmpty()) {
            response.status(HttpStatus.BAD_REQUEST_400);
            return "El parámetro estado es requerido";
        }
        EstadoIndicacion estadoIndicacion = null;
        try {
            estadoIndicacion = EstadoIndicacion.getByName(estado);
        } catch (IllegalArgumentException e) {
            response.status(HttpStatus.BAD_REQUEST_400);
            return "El estado es inválido";
        }
        return indicacionService.buscarPorEstado(estadoIndicacion);
    }

    public Object modificarIndicacionRechazada(Request request, Response response) {
        Long indicacionId = Long.parseLong(request.params("indicacionId"));
        try {
            Indicacion indicacion = indicacionService.modificarRechazada(indicacionId);
            return indicacion.getCodigoIndicacion();
        } catch (BusinessException be) {
            response.status(be.getStatus());
            return be.getMessage();
        }
    }

    public Object getIndicacion(Request request, Response response) {
        Long indicacionId = Long.parseLong(request.params("indicacionId"));
        try {
            return indicacionService.findIndicacion(indicacionId);
        } catch (BusinessException be) {
            response.status(be.getStatus());
            return be.getMessage();
        }
    }

    public Object validarIndicacion(Request request, Response response) {
        Long indicacionId = Long.parseLong(request.params("indicacionId"));
        String email = request.queryParams("email");

        if (email == null || email.isEmpty()) {
            response.status(HttpStatus.BAD_REQUEST_400);
            return "El parámetro email es requerido";
        }

        try {
            indicacionService.validarIndicacion(indicacionId, email);
            return "";
        } catch (BusinessException be) {
            response.status(be.getStatus());
            return be.getMessage();
        }
    }

    public Object enviarIndicacion(Request request, Response response) {
        Long indicacionId = Long.parseLong(request.params("indicacionId"));
        try {
            indicacionService.enviarIndicacion(indicacionId);
            return "";
        } catch (BusinessException be) {
            response.status(be.getStatus());
            return be.getMessage();
        }
    }

    public Object aceptarIndicacion(Request request, Response response) {
        Long indicacionId = Long.parseLong(request.params("indicacionId"));
        String email = request.queryParams("email");

        if (email == null || email.isEmpty()) {
            response.status(HttpStatus.BAD_REQUEST_400);
            return "El parámetro email es requerido";
        }

        try {
            indicacionService.aceptarIndicacion(indicacionId, email);
            return "";
        } catch (BusinessException be) {
            response.status(be.getStatus());
            return be.getMessage();
        }
    }

    public Object rechazarIndicacion(Request request, Response response) {
        Long indicacionId = Long.parseLong(request.params("indicacionId"));
        String email = request.queryParams("email");

        if (email == null || email.isEmpty()) {
            response.status(HttpStatus.BAD_REQUEST_400);
            return "El parámetro email es requerido";
        }

        JsonObject json = gson.fromJson(request.body(), JsonObject.class);
        String motivo = json.get("motivo").getAsString();

        try {
            indicacionService.rechazarIndicacion(indicacionId, email, motivo);
            return "";
        } catch (BusinessException be) {
            response.status(be.getStatus());
            return be.getMessage();
        }
    }

    public List<Medicamento> getMedicamentos(Request request, Response response) {
        return indicacionService.obtenerMedicamentos();
    }
}
