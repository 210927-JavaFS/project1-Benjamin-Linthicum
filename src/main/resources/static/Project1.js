const URL = "http://localhost:8081/";

let loginButton = document.getElementById('loginButton');
let registerButton = document.getElementById('registerButton');
let toRegistrationButton = document.getElementById('toRegistration');
let toLoginButton = document.getElementById('toLogin');
let viewTicketsButton = document.getElementById('viewPastTickets');
let requestButton = document.getElementById('requestReimbursement');

let registerElements = Array.prototype.slice.call(document.getElementsByClassName('registerClass'));
let loginElements = Array.prototype.slice.call(document.getElementsByClassName('loginClass'));
let employeeElements = Array.prototype.slice.call(document.getElementsByClassName('employeeMenu'));
let reimbursementElements = Array.prototype.slice.call(document.getElementsByClassName('newReimbursement'));
navigateToLogin();

loginButton.onclick = login;
registerButton.onclick = register;
toLoginButton.onclick = navigateToLogin;
toRegistrationButton.onclick = navigateToRegistration;
viewTicketsButton.onclick = getReimbursements;
requestButton.onclick = navigateToNewReimbursement;

let currentUser = null;

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
        document.getElementById("welcome").innerText = "Welcome, " + document.getElementById("loginUsername").value + ".";
        currentUser = document.getElementById("loginUsername").value;
        navigateToEmployeeMenu();
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

async function register(){
    let user = {
        username:document.getElementById("registerUsername").value,
        password:document.getElementById("registerPassword").value,
        firstName:document.getElementById("firstName").value,
        lastName:document.getElementById("lastName").value,
        email:document.getElementById("email").value,
        role:"Employee"
    }

    let response = await fetch(URL+"register", {
        method:"POST",
        body:JSON.stringify(user),
        credentials:"include"
    });

    if(response.status===201){
        console.log("Registration successful!");
        navigateToLogin();
    }
    else{
        console.log("Registration failed.");
    }
}

async function getReimbursements(){
    console.log("hi");
    let user = {
        username:currentUser,
        password:document.getElementById("loginPassword").value // filler value that doesn't make chrome angry
    }
    let response = await fetch(URL+"reimbursements_by_username", {
        method:"POST",
        body: JSON.stringify(user),
        credentials:"include"
    });

    if(response.status === 200){
        let data = await response.json();
        populateUserReimbursementTable(data);
    }
    else{
        console.log("uh oh");
    }
}

function populateUserReimbursementTable(data){
    let tbody = document.getElementById("pastTicketsBody");

    tbody.innerHTML="";

    for(let reimbursement of data){
        let row = document.createElement("tr");

        for(let cell in reimbursement){
            let td = document.createElement("td");
            if(cell!="id"){
                td.innerText=reimbursement[cell];
            }
            row.appendChild(td);
        }
        tbody.appendChild(row);
    }
}

function navigateToRegistration(){
    registerElements.forEach(e => e.style.display = "inline");
    loginElements.forEach(e => e.style.display = "none");
    employeeElements.forEach(e => e.style.display = "none")
    reimbursementElements.forEach(e => e.style.display = "none");
    if(document.getElementById("loginFailed")){
        document.getElementById("loginFailed").remove();
    }
}

function navigateToLogin(){
    registerElements.forEach(e => e.style.display = "none");
    loginElements.forEach(e => e.style.display = "inline");
    employeeElements.forEach(e => e.style.display = "none");
    reimbursementElements.forEach(e => e.style.display = "none");
}

function navigateToEmployeeMenu(){
    registerElements.forEach(e => e.style.display = "none");
    loginElements.forEach(e => e.style.display = "none");
    employeeElements.forEach(e => e.style.display = "inline")
    reimbursementElements.forEach(e => e.style.display = "none");
}

function navigateToNewReimbursement(){
    registerElements.forEach(e => e.style.display = "none");
    loginElements.forEach(e => e.style.display = "none");
    employeeElements.forEach(e => e.style.display = "none")
    reimbursementElements.forEach(e => e.style.display = "inline");
}

function viewPastTickets(){

}