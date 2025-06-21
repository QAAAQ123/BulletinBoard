document.getElementById("create").addEventListener("click", createPost);

function createPost() {
    const now = new Date();

    console.log("'post' post request");
    fetch("http://localhost:8000/main", {
        headers: {
            'Content-Type': 'application/json'
        },
        method: "POST",
        body: JSON.stringify({
            title: document.getElementById("title").value,
            content: document.getElementById("content").value,
            updateAt: now.toISOString()
        })
    })
        .then(response => response.json())
        .then(result => {
            console.log("Response", result);
            window.location.href = `http://localhost:8000/main.html`;
        })
        .catch(e => console.error("'post' post request fetch error:", e));
}