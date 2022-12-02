
//Cookie
const cookieArr = document.cookie.split("=")
const patientId = cookieArr[1];

//DOM Elements
const submitForm = document.getElementById("appointment-form")
const requestContainer = document.getElementById("appointment-container") // By ID

//Modal Elements
let requestBody = document.getElementById(`appointment-body`)
let updateAppointmentBtn = document.getElementById('update-appointment-button')

const headers = {
    'Content-Type': 'application/json'
}

//connection address  to appointments controller
const baseUrl = "http://localhost:8080/api/v1/appointments/"

//takes form input, creates an object
const handleSubmit = async (e) => {
    e.preventDefault()
    let bodyObj = {
        body: document.getElementById("appointment-input").value
    }
    await addAppointment(bodyObj);
    //Cleans the form
    document.getElementById("appointment-input").value = ''
}

//actual request to Appointment controller
async function addAppointment(obj) {
    const response = await fetch(`${baseUrl}patient/${patientId}`, {
        method: "POST",
        body: JSON.stringify(obj),
        headers: headers
    })
        .catch(err => console.error(err.message))
    if (response.status == 200) {
        return getAppointments(patientId);
    }
}

async function getAppointments(patientId) {
    await fetch(`${baseUrl}patient/${patientId}`, {
        method: "GET",
        headers: headers
    })
        .then(response => response.json())
        .then(data => createAppointmentCards(data))
        .catch(err => console.error(err))
}

async function handleDelete(appointmentId){
    await fetch(baseUrl + appointmentId, {
        method: "DELETE",
        headers: headers
    })
        .catch(err => console.error(err))

// after deleting draw all cards again
    return getAppointments(patientId);
}

//in order to modify appointment
async function getAppointmentById(appointmentId){
    await fetch(baseUrl + appointmentId, {
        method: "GET",
        headers: headers
    })
        .then(res => res.json())
        .then(data => populateModal(data))
        .catch(err => console.error(err.message))
}

async function handleAppointmentEdit(appointmentId){
    let bodyObj = {
        id: appointmentId,
        body: AppointmentProblem.value
    }

    await fetch(baseUrl, {
        method: "PUT",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
        .catch(err => console.error(err))

    return getAppointments(patientId);
}

const createAppointmentCards = (array) => {
    //clean first HTML container if any has been deleted
    AppointmentContainer.innerHTML = ''
    //for each loop
    array.forEach(obj => {
    //created new div
        let AppointmentCard = document.createElement("div")
        appointmentCard.classList.add("m-2")
        appointmentCard.innerHTML = `
            <div class="card d-flex" style="width: flex; height: 10rem;">
                <div class="card-body d-flex flex-column  justify-content-between" style="height: available">
                    <p class="card-text">${obj.body}</p>
                    <div class="d-flex justify-content-between">
                        <button class="btn " onclick="handleDelete(${obj.id})">Delete</button>
                        <button onclick="getRequestById(${obj.id})" type="button" class="btn btn-primary"
                        data-bs-toggle="modal" data-bs-target="#request-edit-modal">
                        Edit
                        </button>
                    </div>
                </div>
            </div>
        `
        appointmentContainer.append(appointmentCard);
    })
}

function handleLogout(){
    let c = document.cookie.split(";");
    for(let i in c){
        document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT"
    }
}

const populateModal = (obj) =>{
    requestProblem.innerText = ''
    requestBody.innerText = obj.body
    updateAppointmentBtn.setAttribute('data-appointment-id', obj.id)
}


getAppointment(patientId);

//button Event Listener for creation of Appointments
submitForm.addEventListener("submit", handleSubmit)

updateAppointmentBtn.addEventListener("click", (e)=>{
    let appointmentId = e.target.getAttribute('data-appointment-id')
    handleAppointmentEdit(appointmentId);
})