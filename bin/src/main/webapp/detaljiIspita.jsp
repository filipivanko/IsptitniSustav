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
        <div id="floatKontejner">
            <div id="jedansupacZavrseniIspit">
                <div class="GrupaPitanjaZavrseniIspit">
                    <form action="KorisnikServlet">
                        <input type="submit" value="Povratak"/>
                    </form>
                </div>
                <c:forEach items="${sessionScope.odabranaInstancaIspita.instanceGrupaPitanja}" var="grupaPitanja">
                    <div class="GrupaPitanjaZavrseniIspit">
                        <div>
                            <c:out value="${grupaPitanja.naziv}"/> 
                        </div>

                        <c:forEach items="${grupaPitanja.instancePitanja}" var="pitanje">
                            <div class="pitanjeZavrseniIspit">
                                <div>
                                    <c:out value="${pitanje.tekst}"/>  
                                </div>

                                <div id="odgovori">
                                    <c:if test="${pitanje.vrstaOdgovora=='tekst'}">
                                        <br/>
                                        Vaš Odgovor:<c:out value="${pitanje.korisnikovOdgovor}"/> <br/>
                                        Točan Odgovor: <c:out value="${pitanje.tocanOdgovor}"/>
                                    </c:if>
                                    <c:if test="${pitanje.vrstaOdgovora=='radio'}">
                                        <c:forEach items="${pitanje.instanceOdgovora}" var="odgovor">
                                            <div class="odgovorZavrseniIspit">
                                                <c:out value="${odgovor.tekst}"/><br/>
                                            </div> 
                                        </c:forEach>      


                                        Vaš Odgovor:<c:out value="${pitanje.korisnikovOdgovor}"/> <br/>
                                        Točan Odgovor: <c:out value="${pitanje.tocanOdgovor}"/> 
                                    </c:if>
                                </div>
                                <div class="deklaracijaTocnostiPitanja">
                                    <c:if  test="${pitanje.tocnoOdgovoreno==true}" >
                                        <c:out value="Ptianje Točno Odgovoreno     1 BOD"/>
                                    </c:if>
                                    <c:if  test="${pitanje.tocnoOdgovoreno==false}" >
                                        <c:out value="Ptianje Nije Točno Odgovoreno     0 BODOVA"/>
                                    </c:if>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </c:forEach>
                <div class="GrupaPitanjaZavrseniIspit">
                    Ispit:<c:out value="${sessionScope.odabranaInstancaIspita.naziv}"/><br/>
                    Vrijeme Polaganja:<c:out value="${sessionScope.odabranaInstancaIspita.datumPisanjaIspita}"/><br/>
                    Status Ispita: <c:if test="${sessionScope.odabranaInstancaIspita.polozen==true}">
                                    <c:out value="Položen"/>    
                                </c:if>
                                <c:if test="${sessionScope.odabranaInstancaIspita.polozen==false}">
                                    <c:out value="Pad"/>    
                                </c:if><br/>
                    Potreban Prag  Za Prolaz:<fmt:formatNumber minFractionDigits="2" maxFractionDigits="2"> <c:out value="${sessionScope.odabranaInstancaIspita.pragZaProlaz*100}" /></fmt:formatNumber>%<br/>
                    Rezutltat Na Ispitu:<fmt:formatNumber minFractionDigits="2" maxFractionDigits="2"><c:out value="${sessionScope.odabranaInstancaIspita.prosjekRijesenostiIspita*100}" /></fmt:formatNumber>%<br/>
                    
                </div>
            </div>
        </div>

        <div id="footer"></div>
    </body>
</html>

