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
}