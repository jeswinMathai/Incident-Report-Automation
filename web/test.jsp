
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>    
<head>
    
<title></title>
    <script language="javascript">
		function enterNums(){
	
            // invoke public applet method
            var sum = add.add(document.inputt.no.value,document.inputt.nos.value);

            document.getElementById('sum').value=sum;

return false;
		}
               



	</script>
<script src="https://www.java.com/js/deployJava.js"></script>
  <script>
    var attributes = {id:'add', code:'appletcode.NewClass',
        archive:'AppletCode.jar', width:0, height:0};
    var parameters = { fontSize:16, permissions:'sandbox' };
    var version = '1.6';
    deployJava.runApplet(attributes, parameters, version);
</script>
    
</head>

    <body>
      <form id="form" name="inputt" action="result.xhtml" method="post">
    no:<input type="text" name="no"/>
     no:<input type="text" name="nos"/>
     sum: <input type="text" name="sum" id="sum" value="0" />
    <input type="button"  value="Add" onclick="return enterNums()"/>
    <input type="submit" value="Submit" />
</form>
   
    </body>
</html>

