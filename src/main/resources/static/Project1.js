const URL = "http://localhost:8081/";

let loginButton = document.getElementById('loginButton');

loginButton.onclick = login;

async function login(){
    let user = {
        username:document.getElementById("username").value,
        password:document.getElementById("password").value
    }

    let response = await fetch(URL+"login", {
        method:"POST",
        body:JSON.stringify(user),
        credentials:"include"
    });

    if(response.status===200) {
        document.getElementsByClassName("loginClass").innerHTML = '';
        console.log("Login successful!");
    }
    else {
        if(!document.getElementById("loginFailed")){
            let message = document.createElement("p");
            message.id = "loginFailed";
            message.setAttribute("style", "color:red");
            message.innerText = "LOGIN FAILED";
            document.getElementsByClassName("loginClass").appendChild(message);
        }
    }
}