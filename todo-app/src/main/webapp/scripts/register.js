const $name = document.getElementById("inputName");
const $lastName = document.getElementById("inputLastName");
const $email = document.getElementById("inputEmail");
const $username = document.getElementById("inputUsername");
const $password = document.getElementById("inputPassword");
const $passwordR = document.getElementById("inputPasswordRepeat");
const $btnSubmit = document.getElementById("btnSubmit");

let data = {
  name: "",
  lastName: "",
  email: "",
  username: "",
  pass: "",
};

$btnSubmit.disabled = true;

$name.addEventListener("input", () => {
  data.name = $name.value;
  $btnSubmit.disabled = isCompleted();
  $btnSubmit.disabled = false;
});

$lastName.addEventListener("input", () => {
  data.lastName = $lastName.value;
  $btnSubmit.disabled = isCompleted();
});

$email.addEventListener("input", () => {
  data.email = $email.value;
  $btnSubmit.disabled = isCompleted();
});

$username.addEventListener("input", () => {
  data.username = $username.value;
  $btnSubmit.disabled = isCompleted();
});

$password.addEventListener("input", () => {
  samePassword();
  $btnSubmit.disabled = isCompleted();
});

$passwordR.addEventListener('input', () => {
	console.log($passwordR.value);
  samePassword();
  $btnSubmit.disabled = isCompleted();
});

function isCompleted() {
  for (const element in data) {
    if (data[element] === "" || !samePassword()) {
      console.log(data);
      $btnSubmit.disabled = true;
      return true;
    }
  }
  $btnSubmit.disabled = false;
  return false;
}

function samePassword() {
  if ($password.value !== $passwordR.value) {
    $passwordR.style.outline = "rgba(255,0,0,0.8) solid 0.2rem";
    return false;
  } else {
    $passwordR.style.outline = "inherit";
    data.pass = $password.value;
    return true;
  }
}
