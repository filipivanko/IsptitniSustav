<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib prefix="is" uri="/WEB-INF/tlds/ispitniSustavTagovi.tld" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
        <title></title>
    </head>
    <body>
        <is:korisnikHeadder/>

        <div id="jedansupac"> 
            <div id="ispitBocnaTraka">
                <div id="ispitGupaPitanjaBocnaTraka">
                    <form method="post" action="KorisnikServlet">
                        <input type="submit" value ="Povratak"/>
                    </form> 
                </div>

                <c:forEach items="${sessionScope.odabranaInstancaIspita.instanceGrupaPitanja}" var="grupa">                 
                    <div id="ispitGupaPitanjaBocnaTraka">
                        <c:out value="${grupa.naziv}"/>
                    </div>
                    <c:set var="brojPitanja" value="1" scope="page"/>
                    <c:forEach items="${grupa.instancePitanja}" var="pitanje">
                        <a href="<c:url value="OdaberiPitanjeUIspituServlet"><c:param name="IDOdabraneInstancePitanja" value="${pitanje.IDInstancaPitanja}"/></c:url>">
                                <div id="ispitPitanjeBocnaTraka">
                                <c:if test="${pitanje.korisnikovOdgovor!=null}">
                                    <c:out value="O"/>
                                </c:if>
                                <c:out value="${brojPitanja}"/>
                                <c:if test="${pitanje.korisnikovOdgovor!=null}">
                                    <c:out value="O"/>
                                </c:if>
                            </div>
                        </a>

                        <c:set var="brojPitanja" value="${brojPitanja +1}" scope="page"/>
                    </c:forEach>
                </c:forEach>
                <div id="ispitGupaPitanjaBocnaTraka">
                    <form method="post" action="PredajIspitServlet">
                        <input type="submit" value="Predaj Ispit"/>
                    </form>  
                </div>   
            </div>
            <div id="poljeZaPitanjeIOdgovore">
                <div id="prostorZaPitanje">
                    <c:if test="${sessionScope.odabranaInstancaPitanja == null}">
                        <c:out value="Odaberite Pitanje Da Zapocnete Ispit"/><br/>
                    </c:if>
                    <c:if test="${!empty sessionScope.odabranaInstancaPitanja.adresaSlike}"><br/>
                        <img class="slikaPitanja"  src="<c:url value="${sessionScope.odabranaInstancaPitanja.adresaSlike}"/>"/><br/>
                    </c:if>
                    <c:out value="${sessionScope.odabranaInstancaPitanja.tekst}"/>
                </div>
                <div id="prostorZaOdgovore">

                    <c:if test="${sessionScope.odabranaInstancaPitanja.vrstaOdgovora=='radio'}">

                        <form method="post" action="PotvrdiOdgovorRadioServlet">
                            <c:forEach items="${sessionScope.odabranaInstancaPitanja.instanceOdgovora}" var="odgovor">

                                <c:if test="${!empty sessionScope.odabranaInstancaPitanja.adresaSlike}"><br/>
                                    <img class="slikaPOdgovora"  src="<c:url value="${odgovor.adresaSlike}"/>"/>
                                </c:if>
                                <input type="radio" name="odgovor" value="${odgovor.IDInstancaOdgovora}"> &nbsp; <c:out value="${odgovor.tekst}"/> &nbsp; &nbsp; &nbsp; &nbsp;

                            </c:forEach> 
                          <br/>  <input type="submit" value="Potvrdi"/>
                        </form>
                    </c:if>
                    <c:if test="${sessionScope.odabranaInstancaPitanja.vrstaOdgovora=='tekst'}">
                        <form method="post" action="PotvrdiOdgovorTekstServlet">
                            <input type="text" name="odgovor"/>
                            <input type="submit" value="Potvrdi"/>
                        </form><br/>
                    </c:if>
                    <div>
                        <c:if test="${sessionScope.odabranaInstancaPitanja.korisnikovOdgovor!=null}">
                            Odgovoreno:  <c:out value="${sessionScope.odabranaInstancaPitanja.korisnikovOdgovor}"/>
                        </c:if>
                    </div>
                </div>
            </div>


        </div>
        <div id="footer"></div>
    </body>
</html>

