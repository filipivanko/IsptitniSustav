<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@taglib prefix="is" uri="WEB-INF/tlds/ispitniSustavTagovi.tld" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    </head>
    <body>
        <is:adminHeadder/>
        
        <div id="jedansupac">
            <div>
            <form method="post" action="OdjavaServlet">
                    <input type="submit" value="Odjava"/>
                </form>
        </div>
            <div id="pojedinosti">
               Instance Ispita Korisnika<br/>
                <form method="post" action="">
                    <select size="10" name="IDodabranaInstancaIspita"  onchange="this.form.submit()">
                        <c:forEach items="${sessionScope.instanceIspitaKorisnika}" var="instanca">
                            <option value="${instanca.IDInstancaIspita}"><c:out value="${instanca.naziv}"/></option>
                        </c:forEach>  
                    </select>
                    <br/>
                   <input type="submit" value ="Odaberi" onclick="form.action = 'OdaberiKompanijuServlet';"/>
                </form>

            </div>
            <div id="pojedinosti">
            </div>
            <div id="pojedinosti">
            </div>
            <div id="pojedinosti">
            </div>
        </div>
        <div id="footer"></div>
    </body>
</html>

