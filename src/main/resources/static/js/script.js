console.log("script loaded");


//change theme work
let currentTheme=getTheme();

//to run everytime the content is loaded
document.addEventListener('DOMContentLoaded',()=>{
    changeTheme(currentTheme);
})

//TO_DO
function changeTheme(currentTheme){
//set to WEB PAGE
    document.querySelector("html").classList.add(currentTheme);
    //set the listener to change theme button
    const changeThemeButton= document.querySelector('#theme_change_button');
    //change the text of the button
    changeThemeButton.querySelector('span').innerHTML=currentTheme==='dark'?`
    <i class="fa-regular fa-sun"></i> Light`:`<i class="fa-solid fa-moon"></i> Dark`;

    changeThemeButton.addEventListener('click',(event)=>{
        //remove the current theme
        document.querySelector('html').classList.remove(currentTheme);
        if(currentTheme==='dark'){
            currentTheme='light';
        }
        else currentTheme='dark';

        //updation on local storage
        setTheme(currentTheme);
        document.querySelector('html').classList.add(currentTheme);
        changeThemeButton.querySelector('span').innerHTML=currentTheme==='dark'?`
    <i class="fa-regular fa-sun"></i> Light`:`<i class="fa-solid fa-moon"></i> Dark`;

    });
}

//set theme to localstorage
function setTheme(theme){
    localStorage.setItem("theme",theme);
}
//set theme to local storage
function getTheme(){
    let theme =localStorage.getItem('theme');
    if(theme)return theme;
    else return 'light';
}