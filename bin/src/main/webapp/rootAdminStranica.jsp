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
                <h3>Root admin stranica</h3>
                <form method="post" action="OdjavaServlet">
                    <input type="submit" value="Odjava"/>
                </form>
            </div>
              
            <div id="pojedinosti">
                <table>
                    <tr>
                        <td>
                            <b>Sve kompanije</b>  
                        </td>
                        <td>

                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form method="post" action="OdaberiKompanijuServlet">
                                <select size="14" name="IDodabranaKompanija" onchange="this.form.submit()">
                                    <c:forEach items="${sessionScope.sveKompanije}" var="kompanija">
                                            <option value="${kompanija.IDKompanija}"><c:out value="${kompanija.naziv}"/></option>
                                    </c:forEach>  


                                </select>
                                <br/>
                            </form>
                        </td>
                        <td>
                            <b>Odabrana Komopanija</b><br/>
                            <c:out value="${sessionScope.odabranaKompanija.naziv}"/>
                        </td>
                    </tr>
                </table>



            </div>
            <div id="pojedinosti"> 
                <table>
                    <tr>
                        <td>
                            <b> Pojedninosti Odabrane Kompanije</b> 
                        </td>                     
                    </tr>
                    <tr>
                        <td>
                           <b>Naziv:</b>  <c:out value="${sessionScope.odabranaKompanija.naziv}"/><br/>
                           <b>Sjedište:</b>  <c:out value="${sessionScope.odabranaKompanija.sjediste}"/><br/>
                           <b>OIB:</b>  <c:out value="${sessionScope.odabranaKompanija.oib}"/><br/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form method="post" action="KompanijaAdminServlet">
                                <input type="submit" value="Stranica admina kompanije"/>    
                            </form>
                            <hr/>
                            <form>
                                <input type="submit" value ="Obriši Kompaniju" onclick="form.action = 'ObrisiKompanijuServlet';"/>
                            </form>
                            <hr/>
                        </td>


                    </tr>

                    <tr>
                        <td>
                            dodaj kompaniju<br/>
                            <form method="post" action="DodajKompanijuServlet">
                                <table>
                                    <tr>
                                        <td>
                                            Naziv Kopmanije:  
                                        </td>
                                        <td>
                                            <input type="text" name="nazivNoveKompanije"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            Sjedište:  
                                        </td>
                                        <td>
                                            <input type="text" name="sjedisteNoveKompanije"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            OIB:  
                                        </td>
                                        <td>
                                            <input type="text" name="oibNoveKompanije"/>
                                        </td>
                                    </tr>
                                </table>
                                <br/>
                                <input type="submit" value="Dodaj Kompaniju"/>
                            </form>
                        </td>
                    </tr>
                </table>
            </div>
            <div id="pojedinosti">

                <b>Dodaj Admina Kompanije:</b><br/>
                <form method="post" action="DodajAdminaKompanijeServlet">
                    <table>
                        <tr>
                            <td>
                                Korisničko ime: 
                            </td>
                            <td>
                                <input type="text" name="korisnickoImeNoviAdminKompanije"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Zaporka  
                            </td>
                            <td>
                                <input type="text" name="zaporkaNoviAdminKompanije"/>
                            </td>
                        </tr>
                    </table>
                    <input type="submit" value="Dodaj admina kompanije"/>                       
                </form>
            </div>
            <div id="pojedinosti">
                <form method="post" action="OdaberiAdminaKompanijeServlet">
                    <table>
                        <tr>
                            <td>
                                <b>Admini Kompanije</b>  
                            </td>
                        </tr>
                        <tr>
                            <td>

                                <select size="14" name="IDodabraniAdmin" onchange="this.form.submit()">
                                    <c:forEach items="${sessionScope.odabranaKompanija.adminiKompanije}" var="admin">
                                           <c:if test="${admin.razinaovlasti=='kompanija'}">   
                                        <option value="${admin.IDAdmin}"><c:out value="${admin.korisnickoIme}"/></option>
                                                 </c:if>
                                    </c:forEach>
                                </select>
                                <br/>
                            </td>
                            <td rowspan="2">
                                <b>Odabrani Admin 
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                </b><br/>
                                <b>U.N:</b>  <c:out value="${sessionScope.odabraniAdminKompanija.korisnickoIme}"/> <br/>
                                <b>Pass:</b>  <c:out value="${sessionScope.odabraniAdminKompanija.korisnickoIme}"/> <br/>
                                <b>Kompanija:</b><c:out value="${sessionScope.odabranaKompanija.naziv}"/>
                                <hr/>
                                <input type="submit" value ="Obriši Admina" onclick="form.action = 'ObrisiAdminaServlet';"/>
                                <hr/>
                            </td>
                        </tr>
                    </table>
                </form>  
                <br/>

            </div>
        </div>
        <div id="footer"></div>
    </body>
</html>
