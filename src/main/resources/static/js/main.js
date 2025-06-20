addEventListener("DOMContentLoaded",loadPosts);

function loadPosts(){
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
            let individualPost = post.postId;

            title.textContent = post.title;
            title.href="http://localhost:8000/main/"+ individualPost;
            editBtn.textContent = "수정";
            editBtn.type="button";

            editBtn.addEventListener("click",() =>{
                window.location.href="http://localhost:8000/edit.html";});

            li.classList.add("post-item");
            title.classList.add("post-title");
            editBtn.classList.add("edit-button");

            li.appendChild(title);
            li.appendChild(editBtn);
            ol.appendChild(li);
        });
        postTag.appendChild(ol);
    })
    .catch((e) => console.error("'posts' get request fetch error:",e));
}
