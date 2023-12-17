const $profile = document.querySelector("#nav-bar ul > li:first-child");
const $profileMenu = document.querySelector("#nav-bar ul > li:last-child");
const $disableScreen = document.getElementById('disable-screen');

$profile.addEventListener("click", toggleInactive);

$disableScreen.addEventListener('click', toggleInactive);

function toggleInactive() {
    if ($profileMenu.classList.contains("inactive")) {
        $profileMenu.classList.remove("inactive");
        $disableScreen.classList.remove('inactive');
    } else {
        $profileMenu.classList.add("inactive");
        $disableScreen.classList.add("inactive");
    }
}


console.log(username);
const $username = document.querySelector('#nav-bar > ul > li > p');

$username.innerHTML = username;