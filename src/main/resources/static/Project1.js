const URL = "http://localhost:8081/";

let loginButton = document.getElementById('loginButton');
let registerButton = document.getElementById('registerButton');
let toRegistrationButton = document.getElementById('toRegistration');
let toLoginButton = document.getElementById('toLogin');
let viewTicketsButton = document.getElementById('viewPastTickets');
let requestButton = document.getElementById('requestReimbursement');
let submitButton = document.getElementById('submitReimbursement');
let logoutButton = document.getElementById('logout');
let logoutButton2 = document.getElementById('logout2');
let backToMenu1 = document.getElementById('backToEmpMenu');
let viewReimbursementsButton = document.getElementById('viewTickets');

let registerElements = Array.prototype.slice.call(document.getElementsByClassName('registerClass'));
let loginElements = Array.prototype.slice.call(document.getElementsByClassName('loginClass'));
let employeeElements = Array.prototype.slice.call(document.getElementsByClassName('employeeMenu'));
let reimbursementElements = Array.prototype.slice.call(document.getElementsByClassName('newReimbursement'));
let managerElements = Array.prototype.slice.call(document.getElementsByClassName('financeManagerMenu'));
navigate(loginElements);

loginButton.onclick = login;
registerButton.onclick = register;
toLoginButton.onclick = function(){navigate(loginElements);}
toRegistrationButton.onclick = function(){navigate(registerElements);}
viewTicketsButton.onclick = employeeGetReimbursements;
requestButton.onclick = function(){navigate(reimbursementElements);}
submitButton.onclick = submitReimbursement;
backToMenu1.onclick = function(){navigate(employeeElements);}
viewReimbursementsButton.onclick = managerGetReimbursements;
logoutButton.onclick = logout;
logoutButton2.onclick = logout;

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
            navigate(employeeElements);
        }
        else {
            document.getElementById("welcome2").innerText = "Welcome, " + currentUser + ".";
            navigate(managerElements);
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
    navigate(loginElements);
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
        navigate(loginElements);
    }
    else{
        console.log("Registration failed.");
    }
}

async function employeeGetReimbursements(){
    let response = await fetch(URL+"reimbursements/username", {
        method:"POST",
        body: JSON.stringify(currentUser),
        credentials:"include"
    });

    if(response.status === 200){
        let data = await response.json();
        populateReimbursementTable(data, document.getElementById("pastTicketsBody"));
    }
    else{
        console.log("uh oh");
    }
}

async function managerGetReimbursements(){
    let response = await fetch(URL+"reimbursements/status", {
        method:"POST",
        body: JSON.stringify(document.getElementById("statusSelect").value),
        credentials:"include"
    });

    if(response.status === 200){
        let data = await response.json();
        populateReimbursementTable(data, document.getElementById("pastTickets2Body"));
    }
    else{
        //console.log("uh oh");
    }
}

function populateReimbursementTable(data , tbody){

    tbody.innerHTML="";

    let count = 0;
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
        if(tbody.id==="pastTickets2Body" && reimbursement.status === "Pending"){
            let bt = document.createElement("button");
            bt.innerHTML = "Approve";
            bt.id = reimbursement.id + "-approve" + count;
            bt.onclick = function(){approve(reimbursement.id)};
            row.appendChild(bt);
            bt = document.createElement("button");
            bt.innerHTML = "Deny";
            bt.id = reimbursement.id + "-deny" + count;
            bt.onclick = function(){deny(reimbursement.id)};
            row.appendChild(bt);
        }
        tbody.appendChild(row);
        count++;
    }
}

async function approve(reimbursement_id){
    console.log("id: " + reimbursement_id);
    let response = await fetch(URL+"approve", {
        method:"PUT",
        body: JSON.stringify(reimbursement_id),
        credentials:"include"
    });
    if(response.status === 200){
        console.log("Approved!");
    }
    else{
        console.log("uh oh");
    }
}

async function deny(reimbursement_id){
    console.log("author: " + reimbursement_id);
    let response = await fetch(URL+"deny", {
        method:"PUT",
        body: JSON.stringify(reimbursement_id),
        credentials:"include"
    });
    if(response.status === 200){
        console.log("Denied!");
    }
    else{
        console.log("uh oh");
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

    navigate(employeeElements);

}

function navigate(elementsToDisplay){
    console.log('hello');
    registerElements.forEach(e => e.style.display = "none");
    loginElements.forEach(e => e.style.display = "none");
    employeeElements.forEach(e => e.style.display = "none")
    reimbursementElements.forEach(e => e.style.display = "none");
    managerElements.forEach(e => e.style.display = "none");
    elementsToDisplay.forEach(e => e.style.display = "inline");
    if(document.getElementById("loginFailed")){
        document.getElementById("loginFailed").remove();
    }
}

function viewPastTickets(){

}