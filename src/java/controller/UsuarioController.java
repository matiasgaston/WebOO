
package controller;

import persistence.UsuarioDAO;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import transferobject.UsuarioTO;
/**
 *
 * @author Luis
 */
@RequestMapping("usuario") //esta será la url para invocar el controlador
@Controller
public class UsuarioController {
    @RequestMapping(method=RequestMethod.POST) //Método que hace posible llamar al controlador al estilo localhost/proyecto/invitacion.htm
    public String derivar(
            HttpServletRequest request, 
            @RequestParam("action") String action,
            @RequestParam(value="email", required=false) String email, 
            @RequestParam(value="password", required=false) String password,
            ModelMap  model){
        
        String selected =action; //acción que vendrá en la URL
        
        switch(selected){
            case "formcreate":{
                return "index";
            }
            
            case "formsearch":{
               return "search";
            }
            
            case "search":{
                UsuarioDAO dao = new UsuarioDAO();
                
                try{
                    model.addAttribute("listaBusqueda", dao.search(request.getParameter("formname")));
                }catch(SQLException e){
                   return "error";
                }
                return "resultadobusqueda";
            }
            
            case "listall":{
                UsuarioDAO dao = new UsuarioDAO();
                
                try{
                    model.addAttribute("list", dao.readAll());
                }catch(SQLException e){
                    return "error";
                }
                return "listAll";
            }
            case "menu":{
                return "index";
            }
            case "login":{
                return ProcesaLogin(request,email,password);
            }
            case "logout":{
                HttpSession session = request.getSession();
                session.setAttribute("usuario", null);
                return "index";
            }
            default:{
                return "error";
            }
        }//FIN SWITCH
    }//FIN FUNCION DERIVAR

    @RequestMapping(value="inscripcion.htm", method=RequestMethod.POST) //Método que hace posible llamar al controlador al estilo localhost/proyecto/invitacion.htm
    public String inscripcion(
        @RequestParam(value="formname", required=false) String name, 
        @RequestParam(value="formrun", required=false) String run,
        ModelMap  model
        ){

        UsuarioDAO dao = new UsuarioDAO();
        UsuarioTO invitado = new UsuarioTO();
        invitado.setNombre(name);
        invitado.setApellidos(run);
        try{
            dao.create(invitado);
        }catch(SQLException e){
            return "error";
        }    
        return "redirect:/invitacion.htm?action=listall";
        
        
    }//FIN FUNCION PRUEBA
    
    @RequestMapping(value="busqueda.htm", method=RequestMethod.POST) //Método que hace posible llamar al controlador al estilo localhost/proyecto/invitacion.htm
    public String busqueda(
        @RequestParam(value="formname", required=false) String name, 
        ModelMap  model
        ){

        UsuarioDAO dao = new UsuarioDAO();

        try{
            model.addAttribute("listaBusqueda", dao.search(name));
        }catch(SQLException e){
           return "error";
        }
        return "resultadobusqueda";
        
    }//FIN FUNCION PRUEBA
    
    public String ProcesaLogin(
        HttpServletRequest request, 
        String email, 
        String password){
        UsuarioTO envia = new UsuarioTO();
        envia.setMail(email);
        envia.setPassword(password);
        UsuarioDAO cst = new UsuarioDAO();
        if(cst.Login(envia)){
            //Logea
            envia = cst.dataUSuario(envia);
            HttpSession session = request.getSession();
            session.setAttribute("usuario", envia);
            return("index");
        }else{
            //Datos inválidos
            return("fail_login");
        }

        
        
    }
    
}
