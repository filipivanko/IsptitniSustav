<%-- 
    Document   : korisnikstranica
    Created on : 04-Sep-2016, 11:23:05
    Author     : Filip
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib prefix="is" uri="/WEB-INF/tlds/ispitniSustavTagovi.tld" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
        <title>Korisnik <c:out value="${sessionScope.korisnik.korisnickoIme}"/></title>
    </head>
    <body>
        <is:korisnikHeadder/>

        <div id="jedansupac">
            <div>
                <form method="post" action="OdjavaServlet">
                    <input type="submit" value="Odjava"/>
                </form>  
                <c:if test="${sessionScope.admin!=null || sessionScope.odabraniAdminGrupa!=null}">   
                    <form method="post" action="GrupaAdminServlet">
                        <input type="submit" value ="Povratak na Grupa Admin Stranicu"/>
                    </form>
                </c:if>
            </div>
            <div id="podatciOKorisniku">
                Korisnik:<c:out value="${sessionScope.korisnik.ime}"/>  <c:out value="${sessionScope.korisnik.prezime}"/>
             
            </div>  

            <div id="pojedinosti2">

                <table>
                    <tr>
                        <td>
                            Raspoloživi Ispiti
                        </td>
                        <td>

                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form method="post" action="OdaberiInstancuIspitaZaPoganjeServlet">
                                <select size="15" name="IDOdabranogIspitaZaPolaganje" onchange="this.form.submit()">
                                    <c:forEach items="${sessionScope.nezavrseniIspiti}" var="ispit">
                                        <option value="${ispit.IDInstancaIspita}"><c:out value="${ispit.naziv}"/></option>
                                    </c:forEach>
                                </select>
                            </form>
                        </td>
                        <td>
                            <b>Odabrani ispit</b><br/>
                            <b>Naziv:</b><c:out value="${sessionScope.odabranaInstancaIspita.naziv}"/><br/>
                            <c:if test="${sessionScope.admin ==null }">   
                                <form method="post" action="PolaziIspitServlet">
                                    <input type="submit" value="Pokreni Polaganje Ispita"/>
                                </form>
                            </c:if>


                        </td>
                    </tr>
                </table>


            </div>
            <div id="pojedinosti2">
                Završeni Ispiti:<br/>
                <form method="post" action="OdaberiZavrseniIspitServlet">
                    <select size="12" name="IDZavrsenogIspita" id="zavrseniIspit" onchange="this.form.submit()">
                        <c:forEach items="${sessionScope.zavrseniIspiti}" var="ispit">
                            <option value="${ispit.IDInstancaIspita}">

                                <c:if test="${ispit.polozen==true}">
                                    <c:out value="Prolaz -"/>    
                                </c:if>
                                <c:if test="${ispit.polozen==false}">
                                    <c:out value="Pad -"/>    
                                </c:if>
                                (<fmt:formatNumber minFractionDigits="2" maxFractionDigits="2"> <c:out value="${ispit.prosjekRijesenostiIspita*100}"/></fmt:formatNumber>%)
                                <c:out value="${ispit.IDInstancaIspita}"/>

                                <c:out value="${ispit.naziv}"/>
                            <c:out value="${ispit.datumPisanjaIspita}"/>
                            </option>
                            </c:forEach>    
                    </select>  
                    <hr/>
                </form>

                <c:if test="${odabraniZavrseniIspit==null}">
                    <c:out value="Nije Odabran Ni Jedan Ispit"/>
                </c:if><br/>
                <b>Odabrani Ispit:</b>   <c:out value="${odabraniZavrseniIspit.IDInstancaIspita}"/> <c:out value="${odabraniZavrseniIspit.naziv}"/>
            </div>
        </div>
        <div id="footer"></div>
    </body>
</html>
