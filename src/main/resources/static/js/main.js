document.addEventListener("DOMContentLoaded",moveToMain);

function moveToMain(){
    fetch("localhost:8000")
    .then(console.log("패치 데이터"))
    .error((e) => console.error("error occurred: e"));
}