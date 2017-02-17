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
                <b> Kompanija admin stranica</b><br/>
                <c:if test="${sessionScope.admin.razinaovlasti=='root'}">   
                    <form method="post" action="RootAdminServlet">
                          <input type="submit" value ="Povratak Na Root Admin Stranicu"/>
                </form>
                </c:if>
            <form method="post" action="OdjavaServlet">
                    <input type="submit" value="Odjava"/>
                </form>
            
            </div> 
            <div id="pojedinosti6">
                <table>
                    <tr>
                        <td>
                            <b>Svi Admini</b>
                        </td>  
                        <td>
                            <b>Pojedinosti Admina</b>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form method="post" action="OdaberiAdminaGrupaServlet">
                                <select size="10" name="IDodabraniAdminGrupa" onchange="this.form.submit()">
                                    <c:forEach items="${sessionScope.odabranaKompanija.adminiKompanije}" var="admin">
                                        <c:if test="${admin.razinaovlasti=='grupa'}">
                                            <option value="${admin.IDAdmin}"><c:out value="${admin.korisnickoIme}"/></option>
                                        </c:if>
                                    </c:forEach>  
                                </select>
                                <br/>

                            </form>   
                        </td>
                        <td>
                                    
                                
                            <b>U.N:</b><c:out value="${sessionScope.odabraniAdminGrupa.korisnickoIme}"/> <br/>
                              <b>Pass:</b> <c:out value="${sessionScope.odabraniAdminGrupa.zaporka}"/>  <br/>

                                 <form method="post" action="GrupaAdminPrviDolazakServlet">
                                <input type="submit" value="Stranica Admina Grupe"/>
                                </form>
                              <br/>
                                

 <hr/>
                                <form method="post" action="ObrisiAdminaGrupeServlet">
                                    <input type="submit" value="obriši Admina"/>
                                     <hr/>
                                </form>


                                <b> Dodaj novog admina </b> <br/>
                                <form method="post" action ="DodajAdminaGrupaServlet">
                                    Korisnicko Ime:<br/>  <input type="text" name="korisnickoIme"/> <br/>
                                    Zaporka:<br/> <input type="text" name="zaporka"/><br/>
                                    <input type="submit" value="Dodaj novog admina"/>  <br/>
                                </form>



                    
                        </td>
                    </tr>
                </table>
            </div>
            <div id="pojedinosti6">
                <table>
                    <tr> 
                        <td>
                            <b>Odabrani Admin: &nbsp; &nbsp;</b>  <c:out value="${sessionScope.odabraniAdminGrupa.korisnickoIme}"/> <br/>
                        </td>
                        <td>
                           <b> Odabrana Grupa:    </b> <c:out value="${sessionScope.odabranaGrupa.naziv}"/> 
                        </td>
                     
                    </tr>

                    <tr>
                        <td colspan="2" >
                            <div id="centrirani">
                                <form method="post"  action="PoveziGrupuIAdminaServlet">
                                    <input type="submit" value="Dodaj Grupu Adminu"/>
                                </form>
                            </div>
                        </td>
                    </tr> 
                       <tr>

                        <td colspan="2" >
                            <div id="centrirani">
                                <form method="post"  action="PrekiniVezuGrupeIAdminaServlet">
                                    <input type="submit" value="Oduzmi Grupu Adminu"/>
                                </form>
                            </div>

                        </td>
                    </tr>
                    <tr>  
                        <td>  
                            <b> Grupe Admina </b>
                        </td>
                        <td>
                            <b>Admini Grupe</b>
                        </td>
                     

                    </tr>
                    <tr>
                        <td> 

                            <form method="post" action="OdaberiGrupuServlet">
                                <select size="8" name="IDodabranaGrupa" onchange="this.form.submit()">
                                    <c:forEach items="${sessionScope.odabraniAdminGrupa.grupeKojePripadajuAdminu}" var="grupa">
                                        <option value="${grupa.IDGrupa}"><c:out value="${grupa.naziv}"/></option>
                                    </c:forEach>  
                                </select>


                            </form> 
                        </td>
                        <td> 

                            <form method="post" action="OdaberiAdminaGrupaServlet">
                                <select size="8" name="IDodabraniAdminGrupa" onchange="this.form.submit()">
                                    <c:forEach items="${sessionScope.odabranaGrupa.adminiGrupe}" var="admin">
                                        <c:if test="${admin.razinaovlasti=='grupa'}">
                                            <option value="${admin.IDAdmin}"><c:out value="${admin.korisnickoIme}"/></option>
                                        </c:if>
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
                            <b>Sve Grupe </b>
                        </td>
                        <td>
                            <b>Pojedinosti grupe </b>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form method="post" action="OdaberiGrupuServlet">
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

                               <b>Naziv Grupe:</b>   

                                <c:out value="${sessionScope.odabranaGrupa.naziv}"/> <br/> 

                               <b>Kompanija:</b>  

                                <c:out value="${sessionScope.odabranaGrupa.kompanija.naziv}"/>  
                                <br/> <br/> 

                                <hr/>
                                <form method="post" action="ObrisiGrupuServlet">
                                    <input type="submit" value="obriši grupu"/>
                                </form>                             
                                <hr/>
                                <br/>
                                <b> Dodaj novu grupu</b> 

                                <form method="post" action="DodajGrupuServlet">
                                    <table>
                                        <tr>
                                            <td>
                                                Naziv grupe:<br>
                                                <input type="text" name="nazivNoveGrupe"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="2">
                                                <input type="submit" value="Dodaj novu grupu"/> 
                                            </td>

                                        </tr>

                                    </table>
                                </form>
                            </div>
                        </td>
                    </tr>
                </table>

            </div> 

            <div id="pojedinosti6">
                <table>
                    <tr>
                        <td>
                            <b>Svi Kompanije</b>
                        </td>  
                        <td>
                            <b>Pojedinosti Ispita</b>               
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form action="OdaberiIspitServlet">
                                <select size="10" name="IDOdabraniIspit" onchange="this.form.submit()">

                                    <c:forEach items="${sessionScope.odabranaKompanija.ispitiKompanije}" var="ispit">
                                        <option value="${ispit.IDIspit}"><c:out value="${ispit.naziv}"/></option>
                                    </c:forEach> 
                                </select> 
                            </form> 
                        </td>
                        <td>
                            <div>
                               <b>Naziv Ispita:</b>  <c:out value="${sessionScope.odabraniIspit.naziv}"/> <br/>
                               <b>Prag Za Prolaz:</b>  <c:out value="${sessionScope.odabraniIspit.pragZaProlaz}"/><br/>
                                <form method="post" action="ObrisiIspitServlet"> 
                                    <hr/>
                                    <input type="submit" value="Obriši ispit"/>  
                                    <hr/>
                                </form> 
                             
                                <b>Dodaj Novi Ispit</b><br/>
                                <form method="post" action="DodajIspitServlet">
                                    Naziv:<br>
                                    <input type="text" name="nazivNovogIspita"/><br/>
                                    Postotak za prolaz:
                                    <select name="postotakZaProlaz" >
                                        <option value="0.1">10%</option>
                                        <option value="0.2">20%</option>  
                                        <option value="0.3">30%</option>
                                        <option value="0.4">40%</option> 
                                        <option value="0.5" selected="selected">50%</option>
                                        <option value="0.6">60%</option>
                                        <option value="0.7">70%</option>
                                        <option value="0.8">80%</option>
                                        <option value="0.9">90%</option>
                                        <option value="1">100%</option>
                                    </select><br/>
                                    Broj Dana ispit je Aktivan:<br/>
                                    <select name="brojDanaIspitJekativan">
                                         <option value="1" selected="selected">1</option>
                                        <option value="2">2</option>  
                                        <option value="3">3</option>
                                        <option value="4">5</option>  
                                        <option value="4">5</option>
                                    </select>
                                    <input type="submit" value="Dodaj Novi Ispit"/> 
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
                            <b>Odabrani Ispit: 
                                &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                            </b>  <c:out value="${sessionScope.odabraniIspit.naziv}"/> <br/>
                        </td>
                        <td>
                           <b> Odabrana Grupa:    </b> <c:out value="${sessionScope.odabranaGrupa.naziv}"/>  
                        </td>
                       
                    </tr>

                    <tr>

                        <td colspan="2" >
                            <div id="centrirani">
                                <form method="post"  action="PoveziGrupuIIspitServlet">
                                    <input type="submit" value="Dodjeli Ispit Grupi"/>
                                </form>
                            </div>

                        </td>
                    </tr> 
                      <tr>

                        <td colspan="2" >
                            <div id="centrirani">
                                <form method="post"  action="PrekiniVezuGrupeIIspitaServlet">
                                    <input type="submit" value="Ukloni Ispit Iz Grupe"/>
                                </form>
                            </div>

                        </td>
                    </tr>
                    <tr> 
                        <td>  
                            <b> Grupe Na Ispitu</b>
                        </td>
                        <td>
                            <b>Ispiti U Grupi</b>
                        </td>
                    </tr>   

                    <tr>
                        <td> 

                            <form method="post" action="OdaberiGrupuServlet">
                                <select size="8" name="IDodabranaGrupa" onchange="this.form.submit()">
                                    <c:forEach items="${sessionScope.odabraniIspit.grupeKojeKoristeIspit}" var="grupa">
                                        <option value="${grupa.IDGrupa}"><c:out value="${grupa.naziv}"/></option>
                                    </c:forEach>  
                                </select>
                            </form> 
                        </td> 
                        <td> 

                            <form method="post" action="OdaberiIspitServlet">
                                <select size="8" name="IDodabraniIspit" onchange="this.form.submit()">
                                    <c:forEach items="${sessionScope.odabranaGrupa.ispitiGrupe}" var="ispit">
                                        <option value="${ispit.IDIspit}"><c:out value="${ispit.naziv}"/></option>
                                    </c:forEach>  
                                </select>
                            </form>  
                        </td>                 
                    </tr>  
                   
                </table>

            </div>
            <div id="pojedinosti6">
                <form method="post" action="GrupaPitanjaServlet"> 
                    <b>Uredređivanje Ispita</b>  <br/>
                    <input type="submit" value="Uredi Ispite U Kompaniji"/> <br/>
                </form>
                 <form method="post" action="AdministracijaKorisnikaServlet"> 
                    <b>Upravljanje Korisnicima</b>  <br/>
                    <input type="submit" value="Upravljaj Korisnicima"/> <br/>
                </form>
            </div>
        </div>
        <div id="footer"></div>
    </body>
</html>
