<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="stilcss.css"/>
         <%@taglib prefix="is" uri="WEB-INF/tlds/ispitniSustavTagovi.tld" %>
        <title>Prijava</title>
    </head>
    <body>
        <is:adminHeadder/>
        <div id="jedansupac">   
            
            <div id="loginforma">
                <form action="AdminPrijavaServlet" method="post" >

                    <fieldset>
                        <legend>
                            Prijavite se
                        </legend>
                        <table id="tablicaZaPrijavu">
                            <tr>
                                <td>Korisničko ime</td>  
                                <td><input type="text" name="korisnickoIme" id="korisnickoIme"/> </td>
                            </tr>
                            <tr>
                                <td> Zaporka </td>
                                <td>  <input type="password" name="zaporka" id="zaporka"></td>
                            </tr>
                             <tr>
                                 <td colspan="2"> <input type="submit" value="Prijavi se"/> </td>
                            </tr>
                        </table>                 
                    </fieldset>
                </form>  

            </div>
        </div>
   <div id ="footer">
              <a href="korisnikPrijava.jsp">Prijava Korisnik</a>  
            </div>
    </body>
</html>

