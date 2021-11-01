const URL = "http://localhost:8081/";

let loginButton = document.getElementById('loginButton');
let registerButton = document.getElementById('registerButton');
let toRegistrationButton = document.getElementById('toRegistration');
let toLoginButton = document.getElementById('toLogin');
let viewTicketsButton = document.getElementById('viewPastTickets');
let requestButton = document.getElementById('requestReimbursement');
let submitButton = document.getElementById('submitReimbursement');
let logoutButton = document.getElementById('logout');
let backToMenu1 = document.getElementById('backToEmpMenu');

let registerElements = Array.prototype.slice.call(document.getElementsByClassName('registerClass'));
let loginElements = Array.prototype.slice.call(document.getElementsByClassName('loginClass'));
let employeeElements = Array.prototype.slice.call(document.getElementsByClassName('employeeMenu'));
let reimbursementElements = Array.prototype.slice.call(document.getElementsByClassName('newReimbursement'));
let managerElements = Array.prototype.slice.call(document.getElementsByClassName('financeManagerMenu'));
navigateToLogin();

loginButton.onclick = login;
registerButton.onclick = register;
toLoginButton.onclick = navigateToLogin;
toRegistrationButton.onclick = navigateToRegistration;
viewTicketsButton.onclick = getReimbursements;
requestButton.onclick = navigateToNewReimbursement;
submitButton.onclick = submitReimbursement;
backToMenu1.onclick = navigateToEmployeeMenu;
logoutButton.onclick = logout;

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
        currentUser = document.getElementById("loginUsername").value;
        if(await response.json() === "Employee"){
            document.getElementById("welcome").innerText = "Welcome, " + currentUser + ".";
            navigateToEmployeeMenu();
        }
        else {
            document.getElementById("welcome2").innerText = "Welcome, " + currentUser + ".";
            navigateToManagerMenu();
        }
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

function logout(){
    currentUser = null;
    document.getElementById("loginUsername").value = "";
    document.getElementById("loginPassword").value = "";
    navigateToLogin();
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
        let td = document.createElement("td");
        td.innerText="$" + reimbursement.amount;
        row.appendChild(td);
        td = document.createElement("td");
        td.innerText=reimbursement.submitted;
        row.appendChild(td);
        td = document.createElement("td");
        td.innerText=reimbursement.resolved ? reimbursement.resolved : "N/A";
        row.appendChild(td);
        td = document.createElement("td");
        td.innerText=reimbursement.description;
        row.appendChild(td);
        td = document.createElement("td");
        td.innerText=reimbursement.author;
        row.appendChild(td);
        td = document.createElement("td");
        td.innerText=reimbursement.resolver;
        row.appendChild(td);
        td = document.createElement("td");
        td.innerText=reimbursement.status;
        row.appendChild(td);
        td = document.createElement("td");
        td.innerText=reimbursement.type;
        row.appendChild(td);
        /*row.appendChild(document.createElement("td").innerText=reimbursement.amount);
        row.appendChild(document.createElement("td").innerText=reimbursement.submitted);
        row.appendChild(document.createElement("td").innerText=reimbursement.resolved);
        row.appendChild(document.createElement("td").innerText=reimbursement.description);
        row.appendChild(document.createElement("td").innerText=reimbursement.author);
        row.appendChild(document.createElement("td").innerText=reimbursement.resolver);
        row.appendChild(document.createElement("td").innerText=reimbursement.status);
        row.appendChild(document.createElement("td").innerText=reimbursement.type); */
        /*for(let cell in reimbursement){
            let td = document.createElement("td");
            if(cell!="id"){
                td.innerText=reimbursement[cell];
            }
            row.appendChild(td);
        } */
        tbody.appendChild(row);
    }
}

async function submitReimbursement(){
    let reimbursement = {
        amount:document.getElementById("amount").value,
        description:document.getElementById("description").value,
        author:currentUser,
        type:document.getElementById("type").value
    }

    let response = await fetch(URL+"reimbursements", {
        method:"POST",
        body:JSON.stringify(reimbursement),
        credentials:"include"
    });

    if (response.status === 200){
        console.log("Reimbursement submitted successfully.");
    }
    else{
        console.log("uh oh");
    }

    navigateToEmployeeMenu();

}

function navigateToRegistration(){
    registerElements.forEach(e => e.style.display = "inline");
    loginElements.forEach(e => e.style.display = "none");
    employeeElements.forEach(e => e.style.display = "none")
    reimbursementElements.forEach(e => e.style.display = "none");
    managerElements.forEach(e => e.style.display = "none");
}

function navigateToLogin(){
    registerElements.forEach(e => e.style.display = "none");
    loginElements.forEach(e => e.style.display = "inline");
    employeeElements.forEach(e => e.style.display = "none");
    reimbursementElements.forEach(e => e.style.display = "none");
    managerElements.forEach(e => e.style.display = "none");
    if(document.getElementById("loginFailed")){
        document.getElementById("loginFailed").remove();
    }
}

function navigateToEmployeeMenu(){
    registerElements.forEach(e => e.style.display = "none");
    loginElements.forEach(e => e.style.display = "none");
    employeeElements.forEach(e => e.style.display = "inline")
    reimbursementElements.forEach(e => e.style.display = "none");
    managerElements.forEach(e => e.style.display = "none");
}

function navigateToNewReimbursement(){
    registerElements.forEach(e => e.style.display = "none");
    loginElements.forEach(e => e.style.display = "none");
    employeeElements.forEach(e => e.style.display = "none")
    reimbursementElements.forEach(e => e.style.display = "inline");
    managerElements.forEach(e => e.style.display = "none");
}

function navigateToManagerMenu(){
    registerElements.forEach(e => e.style.display = "none");
    loginElements.forEach(e => e.style.display = "none");
    employeeElements.forEach(e => e.style.display = "none")
    reimbursementElements.forEach(e => e.style.display = "none");
    managerElements.forEach(e => e.style.display = "inline");
}

function viewPastTickets(){

}