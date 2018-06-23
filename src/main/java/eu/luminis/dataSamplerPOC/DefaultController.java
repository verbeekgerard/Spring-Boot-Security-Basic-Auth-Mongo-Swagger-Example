package eu.luminis.dataSamplerPOC;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DefaultController {
    @RequestMapping(value = {"/info", "/api", "/swagger", "/"}, method = RequestMethod.GET)
    public String swaggerInfo() {
        return "redirect:swagger-ui.html";
    }
}
