const inputPass = document.getElementById('inputPassword');
const inputPassR = document.getElementById('inputPasswordRepeat');
const showPass = document.querySelector('.showP');
const showPassR = document.querySelector('.showPR');
const hidePass = document.querySelector('.hideP');
const hidePassR = document.querySelector('.hidePR');


showPass.addEventListener('click', () => {
    tooglePassword(inputPass, showPass, hidePass);
});

hidePass.addEventListener('click', () => {
    tooglePassword(inputPass, hidePass, showPass);
});

showPassR.addEventListener('click', () => {
    tooglePassword(inputPassR, showPassR, hidePassR);
});

hidePassR.addEventListener('click', () => {
    tooglePassword(inputPassR, hidePassR, showPassR);
});


function tooglePassword(inputElement, inactivateElement, activateElement) {
    if(inputElement.type == 'text'){
        inputElement.type = 'password';
    } else {
        inputElement.type = 'text';
    }
    activateElement.classList.remove('inactive');
    inactivateElement.classList.add('inactive');
}