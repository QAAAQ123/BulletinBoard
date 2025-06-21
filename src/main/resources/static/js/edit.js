addEventListener("DOMContentLoaded", loadPost);
document.getElementById("editbtn").addEventListener("click", editPost);

function loadPost() {
    const urlParams = new URLSearchParams(window.location.search);
    const id = urlParams.get("postId");

    fetch(`http://localhost:8000/main/${id}`)
        .then(response => response.json())
        .then(post => {
            document.getElementById("title").value = post.title;
            document.getElementById("content").value = post.content;
        });
    console.log("post(id: " + id + ") data loaded");
}

function editPost() {
    const now = new Date();
    console.log("'post' put request");
    const urlParams = new URLSearchParams(window.location.search);
    const id = urlParams.get("postId");

    fetch(`http://localhost:8000/main/${id}`, {
        headers: {
            'Content-Type': 'application/json'
        },
        method: "PUT",
        body: JSON.stringify({
            postId: id,
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
        .catch(e => console.error("'post' put request fetch error:", e));

}