package mx.edu.utez.sda.springmvc.control;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/peliculas")
public class ControlPeliculas {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @Secured("ROLE_RECEP")
    public String index(){
        return "peliculas";
    }

    @RequestMapping(value = "/ninos", method = RequestMethod.GET)
    @Secured({"ROLE_NINO", "ROLE_ADULTO"})
    public String index_nino(){
        return "ninos";
    }

    @RequestMapping(value = "/adultos", method = RequestMethod.GET)
    @Secured("ROLE_ADULTO")
    public String index_adulto(){
        return "adultos";
    }

    @RequestMapping(value = "/comedia", method = RequestMethod.GET)
    @Secured({"ROLE_NINO", "ROLE_ADULTO"})
    public String index_comedia(){
        return "comedia";
    }

    @RequestMapping(value = "/accion", method = RequestMethod.GET)
    @Secured("ROLE_ADULTO")
    public String index_accion(){
        return "accion";
    }

    @RequestMapping(value = "/romance", method = RequestMethod.GET)
    @Secured("ROLE_ADULTO")
    public String index_romance(){
        return "romance";
    }

    @RequestMapping(value = "/terror", method = RequestMethod.GET)
    @Secured("ROLE_ADULTO")
    public String index_terror(){
        return "terror";
    }

}
