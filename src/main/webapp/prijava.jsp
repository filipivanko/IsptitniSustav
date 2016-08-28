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
            <form action="PrijavaServlet" method="post">
                <fieldset>
                    <legend>
                        Prijavite se
                    </legend>
                    Korisničko ime <input type="text" name="korisnickoime" /><br/>                    
                   Zaporka <input type="password" name="lozinka">
                </fieldset>
            </form>
        </div>
        
    </body>
</html>
