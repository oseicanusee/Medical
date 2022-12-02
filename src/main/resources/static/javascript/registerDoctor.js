const registerForm = document.getElementById('register-form') // by id

const headers = {
    'Content-Type':'application/json'
}

//connection address  to patients controller
const baseUrl = 'http://localhost:8080/api/doctors'
//const baseUrl = 'http://localhost:8080/api/v1/doctors'

//takes form input, creates an object
const handleSubmit = async (e) =>{
    e.preventDefault()

    let bodyObj = {
        firstName: document.getElementById('register-doctorFirstName').value,
        lastName: document.getElementById('register-doctorLastName').value,
        doctor_id: document.getElementById('register-doctorId').value,
        email: document.getElementById('register-doctorEmail').value,
        password: document.getElementById('register-password').value
    }
//    console.log(bodyObj);
    //actual request to patients controller
    const response = await fetch(`${baseUrl}/register`, {
        method: "POST",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
        .catch(err => console.error(err.message))
//    console.log(response);

    const responseArr = await response.json()

    if (response.status === 200){
        window.location.replace(responseArr[0])
    }
}

//button Event Listener for creation of new registration
registerForm.addEventListener("submit", handleSubmit)
console.log("doctors");