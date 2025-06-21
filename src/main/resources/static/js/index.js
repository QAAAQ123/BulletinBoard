let link = "http://localhost:8000/main.html";

document.addEventListener("DOMContentLoaded", moveToMain);
function moveToMain() {
    console.log("connected");
    fetch("http://localhost:8000/")
        .then(location.replace(link));
}