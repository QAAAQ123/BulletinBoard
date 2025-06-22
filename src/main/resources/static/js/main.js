addEventListener("DOMContentLoaded", loadPosts);

function loadPosts() {
    console.log("'posts' get request");
    fetch("http://localhost:8000/main")
        .then(response => response.json())
        .then(posts => {
            const postTag = document.getElementById("post");
            const ol = document.createElement("ol");

            posts.forEach((post) => {
                const li = document.createElement("li");
                const title = document.createElement("a");
                const editBtn = document.createElement("button");
                const deletBtn = document.createElement("button");
                let individualPost = post.postId;

                title.textContent = post.title;
                title.href = `http://localhost:8000/indiPost.html?postId=${individualPost}`;
                editBtn.textContent = "수정";
                editBtn.type = "button";
                deletBtn.textContent = "삭제";
                deletBtn.type = "button";

                editBtn.addEventListener("click", () => {
                    window.location.href = `http://localhost:8000/edit.html?postId=${individualPost}`;
                });

                deletBtn.addEventListener("click", 
                    () => deletPost(individualPost));

                li.classList.add("post-item");
                title.classList.add("post-title");
                editBtn.classList.add("edit-button");
                deletBtn.classList.add("delete-button");

                li.appendChild(title);
                li.appendChild(editBtn);
                li.appendChild(deletBtn);
                ol.appendChild(li);
            });
            postTag.appendChild(ol);
        })
        .catch((e) => console.error("'posts' get request fetch error:", e));
}

function deletPost(id) {
    console.log("post(id:" + id + ") delete request");
    fetch("http://localhost:8000/main/" + id, {
        headers: {
            'Content-Type': 'application/json'
        },
        method: "DELETE",
        body: JSON.stringify({
            postId: id
        })
    })
        .then(window.location.href = `http://localhost:8000/main.html`)
        .catch(e => console.error("'post' delete request fetch error:", e));
}