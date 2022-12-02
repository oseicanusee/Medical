const registerForm = document.getElementById('register-form') //  by id

const headers = {
    'Content-Type':'application/json'
}

//connection address  to patients controller
const baseUrl = 'http://localhost:8080/api/v1/patients'

//takes form input, creates an object
const handleSubmit = async (e) =>{
    e.preventDefault()

    let bodyObj = {
        patientName: document.getElementById('register-patientName').value,
        firstName: document.getElementById('register-firstName').value,
        lastName: document.getElementById('register-lastName').value,
        email: document.getElementById('register-email').value,
        birthDate: document.getElementById('register-birthDate').value,
        address: document.getElementById('register-address').value,
        phone: document.getElementById('register-phone').value,
        password: document.getElementById('register-password')
    }

    //actual request to patients controller
    const response = await fetch(`${baseUrl}/register`, {
        method: "POST",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
        .catch(err => console.error(err.message))
    console.log(response);

    const responseArr = await response.json()

    if (response.status === 200){
        window.location.replace(responseArr[0])
    }
}

//button Event Listener for creation of new registration
registerForm.addEventListener("submit", handleSubmit)
console.log("patient");
