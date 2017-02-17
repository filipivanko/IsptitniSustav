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
                Grupa admin:<c:out value="${odabraniAdminGrupa.korisnickoIme}"/>  <br/>
                <c:if test="${sessionScope.admin.razinaovlasti=='kompanija' || sessionScope.admin.razinaovlasti=='root'}">   
                    <form method="post" action="KompanijaAdminServlet">
                        <input type="submit" value ="Povratak na Kompanija Admin Stranicu"/>
                    </form>
                </c:if>
                <form method="post" action="OdjavaServlet">
                    <input type="submit" value="Odjava"/>
                </form>
            </div> 

            <div id="pojedinosti">
                <table>
                    <tr>
                        <td>
                            <b>Grupe: </b><c:out value="${odabraniAdminGrupa.korisnickoIme}"/>
                        </td>
                        <td>

                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form method="post" action="OdaberiGrupuGrupaStranicaServlet">
                                <select size="10" name="IDodabranaGrupa" onchange="this.form.submit()">
                                    <c:forEach items="${sessionScope.odabraniAdminGrupa.grupeKojePripadajuAdminu}" var="grupa">
                                        <option value="${grupa.IDGrupa}"><c:out value="${grupa.naziv}"/></option>
                                    </c:forEach>  
                                </select><br/>
                            </form>   
                        </td>
                        <td>
                            <b>Odabrana grupa</b> <br>
                            <c:out value="${sessionScope.odabranaGrupa.naziv}"/><br/>

                        </td>
                    </tr>
                </table>
                <hr/>
                <form method="post" action="DodajInstancuIspitaSvimKorisnicimaGrupeServlet">
                    <input value="Dodaj Instancu Ispita Svim Korsisnicima Grupe" type="submit"/>
                </form>
                <hr/>
                <c:out value="${sessionScope.porukaIspitDodanGrupi}"/>
                <c:remove var="porukaIspitDodanGrupi" scope="session" />
            </div>
            <div id="pojedinosti">
                <table>
                    <tr>
                        <td>
                            <b>Svi Korisnici u grupi</b>  
                        </td>
                        <td>

                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form method="post" action="OdaberiKorisnikaGrupaStranicaServlet">
                                <select size="10" name="IDodabraniKorisnik" onchange="this.form.submit()">
                                    <c:forEach items="${sessionScope.odabranaGrupa.clanoviGrupe}" var="korisnik">
                                        <option value="${korisnik.IDKorisnik}"><c:out value="${korisnik.korisnickoIme}"/></option>
                                    </c:forEach>  
                                </select>
                                <br/>
                            </form>
                        </td>
                        <td>
                            <b>Odabrani Korisnik</b><br/>
                            <c:out value="${sessionScope.odabraniKorisnk.korisnickoIme}"/>
                        </td>
                    </tr>

                </table>



            </div>
            <div id="pojedinosti">

                <table>
                    <tr>
                        <td>
                            <b>Ispiti U Grupi</b> 
                        </td>
                        <td>

                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form method="post" action="OdaberiIspitGrupaStranicaServlet">
                                <select size="12" name="IDodabraniIspit" onchange="this.form.submit()">
                                    <c:forEach items="${sessionScope.odabranaGrupa.ispitiGrupe}" var="ispit">
                                        <option value="${ispit.IDIspit}"><c:out value="${ispit.naziv}"/></option>
                                    </c:forEach>  
                                </select><br/>

                            </form>     
                        </td>
                        <td>
                            <b>Odabrani Ispit</b><br>
                            <c:out value="${sessionScope.odabraniIspit.naziv}"/>
                        </td>
                    </tr>
                </table>


            </div>

            <div id="pojedinosti">
                <b>Pojedinosti O Korisniku:</b><c:out value="${odabraniKorisnk.korisnickoIme}"/><br/>

                <b>Ime:</b>    <c:out value="${odabraniKorisnk.ime}"/><br/>

                <b>Prezime:</b>  <c:out value="${odabraniKorisnk.prezime}"/><br/>


                <b>Adresa:</b> <c:out  value="${odabraniKorisnk.adresa}"/><br/>


                <b>StrucnaSprema:</b>  <c:out  value="${odabraniKorisnk.strucnaSprema}"/><br/>


                <b>Zanimanje:</b>  <c:out value="${odabraniKorisnk.zanimanje}"/><br/>


                <b>OIB:</b><c:out value="${odabraniKorisnk.oib}"/><br/>


                <b>KorisnickoIme:</b><c:out value="${odabraniKorisnk.korisnickoIme}"/><br/>


                <b>Zaporka:</b>  <c:out value="${odabraniKorisnk.zaporka}"/> <br/> 
                <hr/>
                <form method="post" action="KorisnikServlet">
                    <input value="Pogledaj Rezultate Korisnika" type="submit"/>
                </form>
                <hr/>
                <form method="post" action="DodajInstancuIspitaKorisnikuServlet">
                    <input value="Dodaj Korisniku Instancu Ispita" type="submit"/>
                </form>
                <hr/>
                <c:out value="${sessionScope.porukaIspitDodanKorisniku}"/>
                <c:remove var="porukaIspitDodanKorisniku" scope="session" />
            </div>

        </div> 
        <div id="footer"></div>
    </body>
</html>
