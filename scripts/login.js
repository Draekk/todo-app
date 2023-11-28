const $username = document.getElementById("inputUsername");
const $password = document.getElementById("inputPassword");
const $btnSubmit = document.getElementById("btnSubmit");

let data = {
  username: "",
  pass: "",
};

$btnSubmit.disabled = true;

$username.addEventListener("input", () => {
  data.username = $username.value;
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
