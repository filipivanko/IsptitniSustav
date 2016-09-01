<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="stilcss.css"/>
        <title>Prijava</title>
    </head>
    <body>
        <div id="naslovnaSlika">
            <img src="images/testiranje.jpg"/>
        </div >

        <div id="jedansupac">   
            
            <div id="loginforma">
                <form action="PrijavaServlet" method="post" >

                    <fieldset>
                        <legend>
                            Prijavite se
                        </legend>
                        <table id="tablicaZaPrijavu">
                            <tr>
                                <td>Korisničko ime</td>  
                                <td><input type="text" name="korisnickoime" id="korisnickoIme"/> </td>
                            </tr>
                            <tr>
                                <td> Zaporka </td>
                                <td>  <input type="password" name="zaporka" id="zaporka"></td>
                            </tr>
                        </table>                 
                    </fieldset>
                </form>  

            </div>
        </div>

    </body>
</html>
