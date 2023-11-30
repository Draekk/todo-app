const $email = document.getElementById("inputEmail");
const $password = document.getElementById("inputPassword");
const $btnSubmit = document.getElementById("btnSubmit");

let data = {
  email: "",
  pass: "",
};

$btnSubmit.disabled = true;

$email.addEventListener("input", () => {
  data.email = $email.value;
  $btnSubmit.disabled = isCompleted();
});

$password.addEventListener("input", () => {
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
