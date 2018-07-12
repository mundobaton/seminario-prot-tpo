package edu.uade.seminario.tpo.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import edu.uade.seminario.tpo.exception.BusinessException;
import edu.uade.seminario.tpo.model.Usuario;
import edu.uade.seminario.tpo.service.UsuarioService;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SistemaIndicaciones {

    @Inject
    private UsuarioService usuarioService;

    @Inject
    private Gson gson;

    public Object userLogin(Request request, Response response) {
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

}
