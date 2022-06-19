
public class wifi {
	public static void main(String[] args) {
		System.out.println("Hello");
	}
}


<input id='name' onchange='printName()'/>
<div id='result'></div>

function printName()  {
	  const name = document.getElementById('name').value;
	  document.getElementById("result").innerText = name;
	}