<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Spring Web MVC</title>
		<script>
			function onAddForm()
			{
				x=document.getElementById("Age");
				y=document.getElementById("Name");
				if(y.value.length == 0)
				{
					alert("Please input the \"Name\"!");
					return false;
				}
				if(x.value.length == 0)
				{
					alert("Please input the \"Age\"!");
					return false;
				}
				if(!(/^[0-9]+$/.test(x.value)))
				{
					alert("The \"Age\" is wrong! Please input a integer.");
					return false;
				}
			}
		</script>
		<script>
			function onUpdateForm()
			{
				x=document.getElementById("_Age");
				y=document.getElementById("_Name");
				if(y.value.length == 0)
				{
					alert("Please input the \"Name\"!");
					return false;
				}
				if(x.value.length == 0)
				{
					alert("Please input the \"Age\"!");
					return false;
				}
				if(!(/^[0-9]+$/.test(x.value)))
				{
					alert("The \"Age\" is wrong! Please input a integer.");
					return false;
				}
			}
		</script>
	</head>
	
	<BODY onLoad="onChangeSecType()">

		<form method="post" action = "/spring/database/display" enctype= "application/json" onsubmit= "return onAddForm()">
			<table width="1000">
				<tr>
					<td colspan="2" style="background-color:#FFA500;text-align:center;">
						<h2>Add a person</h2>
					</td>
				</tr>

				<tr>
					<td style="background-color:#FFD700;width:100px;">
 						<b>Name:</b><br>
					</td>
					<td style="background-color:#eeeeee;height:30px;width:400px;">
						<input type="text" name="name" id="Name"><br>
					</td>
				</tr>

				<tr>
					<td style="background-color:#FFD700;width:100px;">
 						<b>Age:</b><br>
					</td>
					<td style="background-color:#eeeeee;height:30px;width:400px;">
						<input type="text" name="age" id="Age"><br>
					</td>
				</tr>

				<tr>
					<td style="background-color:#FFD700;width:100px;">
						<b>Sex:</b><br>
					</td>
					<td style="background-color:#eeeeee;height:30px;">
						<select type="text" name="sex" >
							<option value="male">male</option>
							<option value="female">female</option>
						</select>
					</td>
				</tr>

				<tr>
					<td colspan="2" style="background-color:#FFD700;text-align:center;height:40px">
						<input type="submit" value="Submit"><br></td>
				</tr>
			</table>
		</form>

		<form method="post" action = "/spring/database/display" enctype= "application/json" onsubmit= "return onUpdateForm()">
			<table width="1000">
				<tr>
					<td colspan="2" style="background-color:#FFA500;text-align:center;">
						<h2>Update information</h2>
					</td>
				</tr>

				<tr>
					<td style="background-color:#FFD700;width:100px;">
 						<b>Name:</b><br>
					</td>
					<td style="background-color:#eeeeee;height:30px;width:400px;">
						<input type="text" name="name"  id="_Name"><br>
					</td>
				</tr>

				<tr>
					<td style="background-color:#FFD700;width:100px;">
 						<b>Age:</b><br>
					</td>
					<td style="background-color:#eeeeee;height:30px;width:400px;">
						<input type="text" name="age" id="_Age"><br>
					</td>
				</tr>

				<tr>
					<td style="background-color:#FFD700;width:100px;">
						<b>Sex:</b><br>
					</td>
					<td style="background-color:#eeeeee;height:30px;">
						<select type="text" name="sex" >
							<option value="male">male</option>
							<option value="female">female</option>
						</select>
					</td>
				</tr>

				<tr>
					<td colspan="2" style="background-color:#FFD700;text-align:center;height:40px">
						<input type="submit" value="Update"><br></td>
				</tr>
			</table>
		</form>

		<form method="post" action = "/spring/database/search" enctype= "application/json">
			<table width="1000">
				<tr>
					<td colspan="2" style="background-color:#FFA500;text-align:center;">
						<h2>Search information</h2>
					</td>
				</tr>

				<tr>
					<td style="background-color:#FFD700;width:100px;">
 						<b>Name:</b><br>
					</td>
					<td style="background-color:#eeeeee;height:30px;width:400px;">
						<input type="text" name="sname" ><br>
					</td>
				</tr>

				<tr>
					<td colspan="2" style="background-color:#FFD700;text-align:center;height:40px">
						<input type="submit" value="Search"><br></td>
				</tr>
			</table>
		</form>

		<form method="post" action = "/spring/database/remove" enctype= "application/json">
			<table width="1000">
				<tr>
					<td colspan="2" style="background-color:#FFA500;text-align:center;">
						<h2>Delete a person</h2>
					</td>
				</tr>

				<tr>
					<td style="background-color:#FFD700;width:100px;">
 						<b>Name:</b><br>
					</td>
					<td style="background-color:#eeeeee;height:30px;width:400px;">
						<input type="text" name="rname" ><br>
					</td>
				</tr>

				<tr>
					<td colspan="2" style="background-color:#FFD700;text-align:center;height:40px">
						<input type="submit" value="Delete"><br></td>
				</tr>
			</table>
		</form>

		<form method="post" action = "/spring/database/getall" enctype= "application/json">
			<table width="1000">
				<tr>
					<td colspan="2" style="background-color:#FFA500;text-align:center;">
						<h2>Get all information</h2>
					</td>
				</tr>

				<tr>
					<td colspan="2" style="background-color:#FFD700;text-align:center;height:40px">
						<input type="submit" value="Get"><br></td>
				</tr>
			</table>
		</form>
	</BODY>

</html>