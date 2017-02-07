<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@taglib prefix="is" uri="WEB-INF/tlds/ispitniSustavTagovi.tld" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <title>Dodaj Grupu Pitanja</title>
    </head>
    <body>
        <is:adminHeadder/>
        <div id="jedansupac">
            <div>
                <b>Uređivanje Ispita</b> <br/>
                <form method="post" action="KompanijaAdminServlet">
                    <input type="submit" value ="Povratak"/>
                </form>
                <form method="post" action="OdjavaServlet">
                    <input type="submit" value="Odjava"/>
                </form>
            </div>
            <div id="pojedinosti6">
                <table>
                    <tr>
                        <td>
                            <b>Ispiti Kompanije</b>
                        </td>  
                        <td>
                            <b>Pojedinosti Ispita</b>               
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form action="OdaberiIspitNaGrupiPitanjaServlet">
                                <select size="10" name="IDOdabraniIspit" onchange="this.form.submit()">
                                    <c:forEach items="${sessionScope.odabranaKompanija.ispitiKompanije}" var="ispit">
                                        <option value="${ispit.IDIspit}"><c:out value="${ispit.naziv}"/></option>
                                    </c:forEach> 
                                </select> 
                            </form> 
                        </td>
                        <td>
                            <div>
                                <b>ID Ispita:</b>  <c:out value="${sessionScope.odabraniIspit.IDIspit}"/> <br/>
                                <b>Naziv Ispita:</b>   <c:out value="${sessionScope.odabraniIspit.naziv}"/> <br/>
                                <b>Prag Za Prolaz:</b>  <c:out value="${sessionScope.odabraniIspit.pragZaProlaz}"/> <br/><br/>

                            </div>

                            <br/> 
                        </td>
                    </tr>
                </table>  
            </div>
            <div id="pojedinosti6">




                <table>
                    <tr>
                        <td>
                            <b>Odabrani Ispit:</b> 
                           
                        </td>
                        <td>
                            <b> Odabrana Gr. Pitanja: </b>
                        </td>

                    </tr>
                    <tr>  
                        <td>
                            <c:out value="${sessionScope.odabraniIspit.naziv}"/> 
                        </td>
                        <td>
                        <c:out value="${sessionScope.odabranaGrupaPitanja.nazivGrupe}"/>  
                        </td>

                    </tr>

                    <tr>

                        <td colspan="2" >
                            <div id="centrirani">
                                <form method="post"  action="PoveziIspitIGrupuPitanjaServlet">
                                    <input type="submit" value="Dodaj Grupu Pitanja U Ispit"/>
                                </form>
                            </div>

                        </td>
                    </tr>
                    <tr>

                        <td colspan="2" >
                            <div id="centrirani">
                                <form method="post"  action="PrekiniVezuIspitaIGrupaPitanjaServlet">
                                    <input type="submit" value="Izbaci Grupu Pitanja Iz Ispita"/>
                                </form>
                            </div>

                        </td>
                    </tr>
                    <tr>
                        <td>
                            <b>Grupe u Ispitu</b>
                        </td>
                        <td>  
                          <b> Ispiti Sa Grupom </b>
                        </td>

                    </tr>
                    <tr>
                        <td> 

                            <form method="post" action="OdaberiGrupuPitanjaServlet">
                                <select size="7" name="IDOdabranaGrupaPitanja" onchange="this.form.submit()">
                                    <c:forEach items="${sessionScope.odabraniIspit.grupePitanjaUIspitu}" var="grupaPitanja">
                                        <option value="${grupaPitanja.IDGrupaPitanja}"><c:out value="${grupaPitanja.nazivGrupe}"/></option>
                                    </c:forEach>  
                                </select>



                            </form>  
                        </td>
                        <td> 

                            <form method="post" action="OdaberiIspitNaGrupiPitanjaServlet">
                           <select size="7" name="IDOdabraniIspit" onchange="this.form.submit()">
                                    <c:forEach items="${sessionScope.odabranaGrupaPitanja.ispitiKojiSadrzeGrupuPitanja}" var="ispit">
                                        <option value="${ispit.IDIspit}"><c:out value="${ispit.naziv}"/></option>
                                    </c:forEach>  
                                </select>


                            </form> 
                        </td>

                    </tr>

                </table>
            </div>

            <div id="pojedinosti6">
                <table>
                    <tr>
                        <td>
                            <b>Grupe Pitanja Kompanije</b>
                        </td>  
                        <td>
                            <b>Pojedinosti Grupe Pitanja</b>               
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form action="OdaberiGrupuPitanjaServlet">
                                <select size="10" name="IDOdabranaGrupaPitanja" onchange="this.form.submit()">
                                    <c:forEach items="${sessionScope.odabranaKompanija.grupePitanjaKompanije}" var="grupaPitanja">
                                        <option value="${grupaPitanja.IDGrupaPitanja}"><c:out value="${grupaPitanja.nazivGrupe}"/></option>
                                    </c:forEach> 
                                </select> 
                            </form> 
                        </td>
                        <td>
                            <div>
                                <b>ID Gr. Pitanja:</b>   <c:out value="${sessionScope.odabranaGrupaPitanja.IDGrupaPitanja}"/> <br/>
                                <b>Naziv Gr. Pitanja:</b>   <c:out value="${sessionScope.odabranaGrupaPitanja.nazivGrupe}"/> <br/>
                            </div>
                            <div>
                                <form method="post" action="ObrisiGrupuPitanjaServlet">
                                    <hr/>
                                    <input type="submit" value = "Obriši Grupu Pitanja"/>
                                    <hr/>
                                    <br/>

                                </form>
                            </div>

                            <div>
                                <form method="post" action="DodajGrupuPitanjaServlet">
                                    Naziv Grupe Pitanja:  <input type="text" name="NazivNoveGrupePitanja"/><br/>
                                    Broj Pitanja:<select name="BrojPitanjaUGrupiPitanja">
                                        <option value="2">2</option>  
                                        <option value="3">3</option>
                                        <option value="4">4</option>  
                                        <option value="5">5</option>
                                        <option value="6">6</option>
                                    </select>
                                    <input type="submit" value = "Dodaj Grupu Pitanja"/>
                                </form>
                            </div>
                            <br/> 
                        </td>
                    </tr>
                </table> 

            </div>
            <div id="pojedinosti6">
                <table>
                    <tr>
                        <td>
                            <b>Sva Pitanja</b>
                        </td>  
                        <td>
                            <b>Pojedinosti Pitanja</b>               
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form action="OdaberiPitanjeServlet">
                                <select size="10" name="IDOdabranoPitanje" onchange="this.form.submit()">
                                    <c:forEach items="${sessionScope.odabranaKompanija.pitanjaUKompaniji}" var="pitanje">
                                        <option value="${pitanje.IDPitanje}"><c:out value="${pitanje.tekstPitanja}"/></option>
                                    </c:forEach> 
                                </select> 
                            </form> 
                        </td>
                        <td>
                            <div>
                                <b>Text Pitanja:</b>   <c:out value="${sessionScope.odabranoPitanje.tekstPitanja}"/>
                                <br/>
                                <b>Vrsta Odgovora:</b>   <c:out value="${sessionScope.odabranoPitanje.vrstaOdgovora}"/>
                                <br/>
                                <form method="post" action="ObrisiPitanjeServlet">
                                    <hr/>
                                    <input type="submit" value = "Obriši Pitanje"/>
                                    <hr/>
                                </form>
                                <form method = "post" action="DodajPitanjeServlet">
                                    Text Pitanja: <input type="text" name="textPitanja"/><br/>
                                    Adresa Slike: <input type="text" name="adresaSlike"/><br/>
                                    Vrsta Odgovora:<select name="vrstaOdgovora">
                                        <option value="tekst">text</option>
                                        <option value="radio">radiobutton</option>
                                    </select><br/>
                                    <input type="submit" value = "Dodaj Pitanje Pitanje"/>
                                </form>

                            </div>


                            <br/> 
                        </td>
                    </tr>
                </table>  

            </div>
            <div id="pojedinosti6">





                <table>
                    <tr>
                        <td>
                            <b>Odabrano Pitanje:</b>  
                        </td>
                        <td>
                            <b> Odabrana Gr. Pitanja:    </b> 
                        </td>
                        
                    </tr>
                    <tr>
                        <td>
                            <c:out value="${sessionScope.odabranoPitanje.tekstPitanja}"/>  
                        </td>
                        <td>
                            <c:out value="${sessionScope.odabranaGrupaPitanja.nazivGrupe}"/>  
                        </td>
                        
                    </tr>
                    <tr>

                        <td colspan="2" >
                            <div id="centrirani">
                                <form method="post"  action="PoveziPitanjeIGrupuPitanjaServlet">
                                    <input type="submit" value="Dodaj Pitanje U Grupu Pitanja"/>
                                </form>
                            </div>

                        </td>
                    </tr> 
                    <tr>

                        <td colspan="2" >
                            <div id="centrirani">
                                <form method="post"  action="PrekiniVezuPitanjaIGrupePitanjaServlet">
                                    <input type="submit" value="Izbaci Pitanje Iz Grupe Pitanja"/>
                                </form>
                            </div>

                        </td>
                    </tr>
                    <tr> 
                        <td>  
                            <b> Grupe Sa Pitnjem </b>
                        </td>
                        <td>
                            <b>Pitanja u Grupi</b>
                        </td>
                       

                    </tr>
                    <tr>   
                        <td> 

                            <form method="post" action="OdaberiGrupuPitanjaServlet">
                                <select size="7" name="IDOdabranaGrupaPitanja" onchange="this.form.submit()">
                                    <c:forEach items="${sessionScope.odabranoPitanje.grupePitanjaKojaSadrzePitanje}" var="grupaPitanja">
                                        <option value="${grupaPitanja.IDGrupaPitanja}"><c:out value="${grupaPitanja.nazivGrupe}"/></option>
                                    </c:forEach>  
                                </select>



                            </form>  
                        </td>
                        <td> 
                            <form method="post" action="OdaberiPitanjeServlet">
                                <select size="7" name="IDOdabranoPitanje" onchange="this.form.submit()">
                                    <c:forEach items="${sessionScope.odabranaGrupaPitanja.pitanjaUGrupi}" var="pitanje">
                                        <option value="${pitanje.IDPitanje}"><c:out value="${pitanje.tekstPitanja}"/></option>
                                    </c:forEach>  
                                </select>
                            </form> 
                        </td>
                        

                    </tr>

                </table>

            </div>
            <div id="pojedinosti6">

                <table>
                    <tr>
                        <td>
                            <b>Odgovori</b>
                        </td>  
                        <td>
                            <b>Pojedinosti Odgovora</b>               
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form action="OdaberiOdgovorServlet">
                                <select size="10" name="IDodabraniOdgovor" onchange="this.form.submit()">
                                    <c:forEach items="${sessionScope.odabranoPitanje.odgovori}" var="odgovor">
                                        <option value="${odgovor.IDOdgovor}"><c:out value="${odgovor.tekstOdgovora}"/></option>
                                    </c:forEach> 
                                </select> 
                            </form> 
                        </td>
                        <td>

                            <b>Text Odgovora:</b>   <c:out value="${sessionScope.odabraniOdgovor.tekstOdgovora}"/> <br/>
                             <b>Točan:</b>   <c:out value="${sessionScope.odabraniOdgovor.tocanNetocanOdgovor}"/> <br/>

                            <form method="post" action="ObrisiOdgovorServlet">
                                <hr/>
                                <input type ="submit" value="Obriši Odgovor"/>
                                <hr/>
                            </form>


                            <b>Dodaj Odgovor</b>
                            <form method="post" action="DodajOdgovorServlet">
                                Text Odgovora:<input type="text" name="tekstOdgovora"/>
                                Točan Ili Netočan 
                                <select name="tocanNetocanOdgovor">
                                    <option value="tocan">
                                        točan
                                    </option>
                                    <option value="netocan">
                                        netočan
                                    </option>
                                </select><br/>
                                Slika:<input type="text" name="adresaSlike"/><br/>
                                <input type ="submit" value="Dodaj odgovor"/>
                            </form>

                            <br/> 
                        </td>
                    </tr>
                </table>  
            </div>


        </div>


        <div id="footer"></div>
    </body>
</html>
