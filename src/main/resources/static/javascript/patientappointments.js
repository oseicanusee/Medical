//Cookie
const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];

//DOM Elements
const submitForm = document.getElementById("request-form")
//const requestContainer = document.getElementById("request-container") // By ID


const headers = {
    'Content-Type': 'application/json'
}

const baseUrl = "http://localhost:8080/api/v1/appointments/"

const handleSubmit = async (e) => {
    e.preventDefault()
//    let today = new Date();

//    const imageUrl = await uploadImage();

    let bodyObj = {
        department: document.getElementById("department").value,
        appcategory: document.getElementById("appcategory").value,
        doctor: document.getElementById("doctor").value,
        status: "upcoming"
    }

    await addAppointment(bodyObj);

}

async function addAppointment(obj) {
    const response = await fetch(`${baseUrl}patient/${patientId}`, {
        method: "POST",
        body: JSON.stringify(obj),
        headers: headers
    })
        .catch(err => console.error(err.message))
    if (response.status == 200) {
//        await sendMessageToDoctor();
        window.location.replace("http://localhost:8080/appointments.html")
    }
}


async function sendMessageToDoctor() {
//    console.log('send message')
    const turl = "";
    department = document.getElementById("department").value;
    appcategory = document.getElementById("appcategory").value;
    doctor = document.getElementById("doctor").value,

    const response = await axios.post;
}

async function getAppointments(patientId) {
//    console.log("started get requests")
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

    return getAppointments(patientId);
}

async function handleDelete(requestId){
    await fetch(baseUrl + requestId, {
        method: "DELETE",
        headers: headers
    })
        .catch(err => console.error(err))

    return getRequests(userId);
}

const createAppointmentCards = (array) => {
//    console.log("Started create request cards")
    appointmentContainer.innerHTML = ''
    array.forEach(obj => {
        if(obj.status=="upcoming") {
            let appointmentCard = document.createElement("div")
            requestCard.classList.add("col")
            requestCard.innerHTML = `
                <div class="card d-flex" style="width: flex; height: 5rem;">
                    <div class="card-body d-flex flex-column  justify-content-between" style="height: available">
                        <p class="card-text"><b></b> ${obj.department}</p>
                        <p class="card-text"><b></b> ${obj.appcategory}</p>
                        <p class="card-text"><b>Doctor:</b> ${obj.doctor}</p>
                        <p class="card-text"><b>Time:</b> ${obj.rTime}</p>
                        <p class="card-text"><b>Date:</b> ${obj.rDate}</p>
                        <img class="card-img-bottom" src="${obj.description}">
                        <div class="d-flex justify-content-end">
                          <button class="btn btn-delete right" onclick="handleDelete(${obj.id})">Delete</button>
                        </div>
                    </div>
                </div>
            `
            requestContainer.append(requestCard)
        }
    })
}


function handleLogout(){
    let c = document.cookie.split(";");
    for(let i in c){
        document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT"
    }
}

submitForm.addEventListener("submit", handleSubmit)
