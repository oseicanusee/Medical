let loginForm = document.getElementById('login-form') //by input id

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
        email: document.getElementById('login-doctorEmail').value, //by input id
        password: document.getElementById('login-password').value //by input id
    }

    //actual request to patients controller
    const response = await fetch(`${baseUrl}/login`, {
        method: "POST",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
        .catch(err => console.error(err.message))

    const responseArr = await response.json()

    if (response.status === 200){
        document.cookie = `doctorId=${responseArr[1]}`
        window.location.replace(responseArr[0])
    }
}

//button Event Listener for a new login
loginForm.addEventListener("submit", handleSubmit)