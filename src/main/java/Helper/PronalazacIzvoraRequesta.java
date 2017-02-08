
package Helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class PronalazacIzvoraRequesta {
    
    
    public String nadjiIzvor(HttpServletRequest request){
    
    String[] dijeloviRequesta = request.getRequestURI().split("/");
    String pozivniServlet = dijeloviRequesta[dijeloviRequesta.length-1];
    return pozivniServlet;
    }
    
}
