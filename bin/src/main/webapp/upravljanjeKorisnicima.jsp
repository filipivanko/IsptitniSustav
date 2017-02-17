<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                 <b>Uparvljanje Korisnicima</b><br/>
                 <form method="post" action="KompanijaAdminServlet">
                    <input type="submit" value ="Povratak"/>
                </form>
                <form method="post" action="OdjavaServlet">
                    <input type="submit" value="Odjava"/>
                </form>
            </div>
           
            <div id="pojedinosti">
                <table>
                    <tr>
                        <td>
                            <b>Svi Korisnici</b>   
                        </td>
                         <td>
                             <b>Pojedinosti Korisnika</b>
                        </td>
                    </tr>
                    <tr>
                        <td>
                                <form method="post" action="OdaberiKorisnikaServlet">
                    <select size="10" name="IDodabraniKorisnik"  onchange="this.form.submit()">
                        <c:forEach items="${sessionScope.odabranaKompanija.korisniciKompanije}" var="korisnik">
                            <option value="${korisnik.IDKorisnik}"><c:out value="${korisnik.korisnickoIme}"/></option>
                        </c:forEach>  
                    </select>
                    <br/>                 
                </form>
                        </td>  
                        <td>
                             <b>Odabrani Korisnik:</b><br/>  <c:out value="${sessionScope.odabraniKorisnk.korisnickoIme}"/> 
                             <br/>
                         
                             <form method = "post" action="ObrisiKorisnikaServlet"> 
                                 <hr/>
                                 <input type="submit" value="Obriši Korisnika"/>
                                 <hr/>
                             </form>
                          
                        </td>
                        
                    </tr>
                </table>
              
            </div>
            <div id="pojedinosti">
                <table>
                    <tr>
                        <td>
                            <b>Sve Grupe </b>
                        </td>
                        <td>
                            <b>Pojedinosti grupe </b>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form method="post" action="OdaberiGrupuNaSrtraniciKorisnikaServlet">
                                <select size="10" name="IDodabranaGrupa" onchange="this.form.submit()">
                                    <c:forEach items="${sessionScope.odabranaKompanija.grupeUKompaniji}" var="grupa">
                                        <option value="${grupa.IDGrupa}"><c:out value="${grupa.naziv}"/></option>
                                    </c:forEach>  
                                </select>
                                <br/>
                            </form>   
                        </td>
                        <td>
                            <div>

                                <b>Odabrana Grupa: </b><br/>

                                <c:out value="${sessionScope.odabranaGrupa.naziv}"/> <br/> 

                                Kompanije: 

                                <c:out value="${sessionScope.odabranaGrupa.kompanija.naziv}"/>  <br/> 

                            </div>
                        </td>
                    </tr>
                </table>
            </div>
            <div id="pojedinosti">
                 <table>
                    <tr>
                        <td>
                          <b>Odabrani Korisnik:</b>  <c:out value="${sessionScope.odabraniKorisnk.korisnickoIme}"/> 
                        </td>
                        <td>
                           <b> Odabrana Grupa:    </b> <c:out value="${sessionScope.odabranaGrupa.naziv}"/> 
                        </td>
                      
                      
                        
                    </tr>
                    <tr>
                        <td colspan="2" >
                            <div id="centrirani">
                                <form method="post"  action="PoveziGrupuIKorisnikaServlet">
                                    <input type="submit" value="Dodaj Korisnika U Grupu"/>
                                </form>
                            </div>
                        </td>
                    </tr> 
                         <tr>
                        <td colspan="2" >
                            <div id="centrirani">
                                <form method="post"  action="PrekiniVezuGrupeIKorisnikaServlet">
                                    <input type="submit" value="Izbaci Korisnika Iz Grupe"/>
                                </form>
                            </div>
                        </td>
                    </tr> 
                    <tr> 
                        <td>  
                            <b>Korisnikove Grupe</b>
                        </td>
                        <td>
                            <b>Korisnici U Grupi</b>
                        </td>  
                    </tr>   
                    <tr>
                        <td> 
                            <form method="post" action="OdaberiGrupuNaSrtraniciKorisnikaServlet">
                                <select size="6" name="IDodabranaGrupa" onchange="this.form.submit()">
                                    <c:forEach items="${sessionScope.odabraniKorisnk.grupeKojimaKorisnikPripada}" var="grupa">
                                        <option value="${grupa.IDGrupa}"><c:out value="${grupa.naziv}"/></option>
                                    </c:forEach>  
                                </select>
                            </form> 
                        </td> 
                        <td> 
                            <form method="post" action="OdaberiKorisnikaServlet">
                                <select size="6" name="IDodabraniKorisnik" onchange="this.form.submit()">
                                    <c:forEach items="${sessionScope.odabranaGrupa.clanoviGrupe}" var="korisnik">
                                        <option value="${korisnik.IDKorisnik}"><c:out value="${korisnik.korisnickoIme}"/></option>
                                    </c:forEach>  
                                </select>
                            </form>  
                        </td>
                         
                    </tr>     
                </table>
            </div>
            <div id="pojedinosti">
                <b>Dodaj Novog Korisnika</b>
                <form method="post" action="DodajKorisnikaServlet">
                    <table>
                        <tr>
                            <td>
                                    Ime:
                            </td>
                            <td>
                                <input type="text" name="ime">
                            </td>
                            
                        </tr>
                        <tr>
                            <td>
                                Prezime:
                            </td>
                            <td>
                                <input type="text" name="prezime">
           
                            </td>
                            
                        </tr>
                        <tr>
                            <td>
                                 Adresa:
                            </td>
                            <td>
                                <input type="text" name="adresa">
           
                            </td>
                            
                        </tr>
                        <tr>
                            <td>
                                 StrucnaSprema:
                            </td>
                            <td>
                                <input type="text" name="strucnaSprema">
           
                            </td>
                            
                        </tr>
                        <tr>
                            <td>
                                 Zanimanje:
                            </td>
                            <td>
                                <input type="text" name="zanimanje">
           
                            </td>
                            
                        </tr>
                        <tr>
                            <td>
                                 OIB:
                            </td>
                            <td>
                                <input type="text" name="oib">
          
                            </td>
                            
                        </tr>
                        <tr>
                            <td>
                                  KorisnickoIme:
                            </td>
                            <td>
                                <input type="text" name="korisnickoIme">
           
                            </td>
                            
                        </tr>
                        <tr>
                            <td>
                                 Zaporka:
                            </td>
                            <td>
                                <input type="text" name="zaporka">
                            </td>
                            
                        </tr>
                        <tr>
                            <td colspan="2" id="centrirani">
                                <hr/>
                                <input type="submit" value="Dodaj Novog Korisnika" />
                            </td>
                        </tr>
                    </table>
        
                </form>
            </div>
        </div>
        <div id="footer"></div>
    </body>
</html>
