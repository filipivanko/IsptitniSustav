package HelperTest;

import Helper.PronalazacIzvoraRequesta;
import javax.servlet.http.HttpServletRequest;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author Filip
 */
public class PronalazacIzvoraRequestaTest {
    @Test
    public void nadjiIzvorTest() {
        String rezultat;
        PronalazacIzvoraRequesta pronalazac = new PronalazacIzvoraRequesta();
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        
        when(mockRequest.getRequestURI()).thenReturn("/ispitnisustav/KorisnikServlet");
        
        rezultat = pronalazac.nadjiIzvor(mockRequest);
        assertEquals("KorisnikServlet", rezultat);

    }
}
