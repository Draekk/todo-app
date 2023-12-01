const $email = document.getElementById("inputEmail");
const $password = document.getElementById("inputPassword");
const $btnSubmit = document.getElementById("btnSubmit");

let data = {
  email: $email.value,
  pass: $password.value,
};

$btnSubmit.disabled = true;

$email.addEventListener("input", () => {
  data.email = $email.value;
  $btnSubmit.disabled = isCompleted();
});


$password.addEventListener("input", () => {
  data.email = $email.value;
  data.pass = $password.value;
  $btnSubmit.disabled = isCompleted();
});

function isCompleted() {
  for (const element in data) {
    if (data[element] === "") {
      $btnSubmit.disabled = true;
      return true;
    }
  }
  $btnSubmit.disabled = false;
  return false;
}

//------------------------------------------------------------

const url = window.location.search;
const parameters = new URLSearchParams(url);
const message = parameters.get('message');

if(message != null) {
  alert(message);
}