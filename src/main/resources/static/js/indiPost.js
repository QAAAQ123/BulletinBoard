addEventListener("DOMContentLoaded", loadPostAndComment);

function loadPostAndComment() {
    const urlParams = new URLSearchParams(window.location.search);
    const id = urlParams.get("postId");
    console.log(`post(id:${id}) and comment get`);
    const postHTMLTag = document.getElementById("post");
    const commentHTMLTag = document.getElementById("comment");

    fetch(`http://localhost:8000/main/${id}`)
        .then(response => response.json())
        .then(article => {
            console.log(article);
            const postContent = document.createElement("p");
            const postTitle = document.createElement("h1");
            const postUpdateAt = document.createElement("label");
            const createCommentBtn = document.createElement("button");
            const commentol = document.createElement("ol");

            postTitle.appendChild(document.createTextNode(article.title));
            postContent.appendChild(document.createTextNode(article.content));
            postUpdateAt.appendChild(document.createTextNode(article.updateAt));

            postTitle.id = "postTitle";
            postContent.id = "postContent";
            postUpdateAt.id = "postUpdateAt";
            createCommentBtn.id = "createBtn";
            createCommentBtn.textContent = "댓글 생성";
            createCommentBtn.type = "button";

            postHTMLTag.appendChild(postTitle);
            postHTMLTag.appendChild(postContent);
            postHTMLTag.appendChild(postUpdateAt);
            document.body.appendChild(postHTMLTag);

            createCommentBtn.addEventListener("click", () => createComment(id));

            article.commentList.forEach(comment => {
                console.log(comment);

                const commentli = document.createElement("li");
                const commentContent = document.createElement("span");
                const commentUpdatedAt = document.createElement("label");
                const commentEditBtn = document.createElement("button");
                const commentDeleteBtn = document.createElement("button");
                let commentId = comment.commentId;

                commentContent.textContent = comment.commentContent;
                commentUpdatedAt.textContent = comment.commentUpdatedAt;
                commentEditBtn.textContent = "수정";
                commentEditBtn.type = "button";
                commentDeleteBtn.textContent = "삭제";
                commentDeleteBtn.type = "button";

                commentEditBtn.addEventListener("click",
                    () => editComment(id, commentId, comment.commentContent));
                commentDeleteBtn.addEventListener("click",
                    () => deleteComment(id, commentId));

                commentContent.id = "commentContent";
                commentUpdatedAt.id = "commentUpdatedAt";
                commentEditBtn.id = "commentEditBtn";
                commentDeleteBtn.id = "commentDeleteBtn";

                commentli.appendChild(commentContent);
                commentli.appendChild(commentUpdatedAt);
                commentli.appendChild(commentEditBtn);
                commentli.appendChild(commentDeleteBtn);

                commentol.appendChild(commentli);
                commentHTMLTag.append(commentol);
                document.body.appendChild(commentHTMLTag);
            });
            document.body.appendChild(createCommentBtn);
        })
        .catch(e => console.error(`post(id:${id}) and comment get fetch error:  ${e}`));
}


function editComment(id, commentId, commentContent) {
    console.log("'comment' put request");
    let commentPrompt = prompt("댓글 수정", commentContent);
    const now = new Date();

    if (commentPrompt != null) {
        fetch(`http://localhost:8000/main/${id}/${commentId}`, {
            headers: {
                'Content-Type': 'application/json'
            },
            method: "PUT",
            body: JSON.stringify({
                "commentId": commentId,
                "commentContent": commentPrompt,
                "commentUpdatedAt": now.toISOString()
            })
        })
            .then(response => response.json())
            .then(result => {
                console.log("Response", result);
                window.location.href = `http://localhost:8000/indiPost.html?postId=${id}`;
            })
            .catch(e => console.error("'comment' put request fetch error:", e));
    }


}

function deleteComment(id, commentId) {
    console.log("comment(id:" + commentId + ") delete request");
    fetch(`http://localhost:8000/main/${id}/${commentId}`, {
        headers: {
            'Content-Type': 'application/json'
        },
        method: "DELETE",
        body: JSON.stringify({
            "commentId": commentId
        })
    })
        .then(window.location.href = `http://localhost:8000/indiPost.html?postId=${id}`)
        .catch(e => console.error("'comment' delete request fetch error:", e));
}

function createComment(postId) {
    const now = new Date();
    console.log("comment create request");
    let createPrompt = prompt("새 댓글 생성");
    //if (createPrompt != null) {
        fetch(`http://localhost:8000/main/${postId}`, {
            headers: {
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify({
                "commentContent": createPrompt,
                "commentUpdatedAt": now.toISOString()
            })
        })
            .then(response => response.json())
            .then(result => {
                console.log("Response", result);
                window.location.href = `http://localhost:8000/indiPost.html?postId=${postId}`;
            })
            .catch(e => {
                console.error("comment post fetch error: ", e);
            })
    //}
}