const URL = "http://localhost:8081/";

let loginButton = document.getElementById('loginButton');
let toRegistrationButton = document.getElementById('toRegistration');
let toLoginButton = document.getElementById('toLogin');

let registerElements = Array.prototype.slice.call(document.getElementsByClassName('registerClass'));
let loginElements = Array.prototype.slice.call(document.getElementsByClassName('loginClass'));
registerElements.forEach(e => e.style.display = "none");

loginButton.onclick = login;
toLoginButton.onclick = navigateToLogin;
toRegistrationButton.onclick = navigateToRegistration;

async function login(){
    let user = {
        username:document.getElementById("loginUsername").value,
        password:document.getElementById("loginPassword").value
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
            document.getElementsByClassName("loginClass")[0].appendChild(message);
        }
    }
}

function navigateToRegistration(){
    registerElements.forEach(e => e.style.display = "inline");
    loginElements.forEach(e => e.style.display = "none");
}

function navigateToLogin(){
    registerElements.forEach(e => e.style.display = "none");
    loginElements.forEach(e => e.style.display = "inline");
}