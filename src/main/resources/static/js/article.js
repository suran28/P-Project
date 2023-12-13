
document.addEventListener("DOMContentLoaded", () => {

    console.log(article)
    if (article !== null){
        const title = document.getElementById("title")
        title.setAttribute("disabled", "true");
        title.value = article.title

        const writer = document.getElementById("writer")
        writer.setAttribute("disabled", "true");
        writer.value = "작성자 | " + article.writer

        const content = document.getElementById("content")
        content.setAttribute("disabled", "true");
        content.textContent = article.body

        const updateBtn = document.querySelector(".update")
        const deleteBtn = document.querySelector(".delete")
        if(article.writerChk === true) {
            updateBtn.style.display = "block"
            deleteBtn.style.display = "block"
        }

        const commentCon = document.querySelector(".commentList")

        for(let i = 0; i < commentList.length; i++){
            const comment = document.createElement("div")
            comment.className = "comment"
            commentCon.appendChild(comment)

            const body = document.createElement("p")
            body.className ="body"
            body.textContent = commentList[i].writer + ' | ' + commentList[i].body
            comment.appendChild(body)

            const regDate = document.createElement("p")
            regDate.className = "regDate"
            regDate.textContent = commentList[i].regDate
            comment.appendChild(regDate)
            console.log(commentList[i].regDate)
        }

    }else {
        let storedUname = getCookie('username');

        const writer = document.getElementById("writer")
        writer.setAttribute("disabled", "true");
        writer.value = "작성자 | " + storedUname

        const articleSend = document.querySelector(".articleSend")
        const updateBtn = document.querySelector(".btnCon .update")
        const deleteBtn = document.querySelector(".btnCon .delete")
        const commentBtn = document.querySelector(".commentBtn")
        const boardComment = document.querySelector(".boardComment")

        articleSend.style.display = "block"
        updateBtn.style.display = "none"
        deleteBtn.style.display = "none"
        commentBtn.style.display = "none"
        boardComment.style.display = "none"
    }
})

//게시글 작성
function articleSendBtnClick() {

    var title = document.getElementById("title").value;
    var content = document.getElementById("content").value;

    const currentUrl = window.location.href;
    const urlParts = currentUrl.split('/');
    const boardCode = urlParts[4];

    fetch(`/board/${boardCode}/article/new`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            title: title,
            body: content,
        })
    })
        .then(response => response.text())
        .then(data => {
            console.log(data);
            window.location.href = data
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

//게시글 수정 버튼
function updateBtnClick() {

    const title = document.getElementById("title")
    title.removeAttribute("disabled");
    const content = document.getElementById("content")
    content.removeAttribute("disabled");

    title.value = article.title
    content.textContent = article.body

    const update = document.querySelector(".update")
    update.style.display = "none";
    const updateSend = document.querySelector(".updateSend")
    updateSend.style.display = "block";
}

//게시글 수정 내용 전송
function updateSendBtnClick() {

    var updatedTitle = document.getElementById("title").value;
    var updatedContent = document.getElementById("content").value;

    const currentUrl = window.location.href;
    const urlParts = currentUrl.split('/');
    const boardCode = urlParts[4];
    const articleId = urlParts[6];
    console.log(boardCode, articleId)

    fetch(`/board/${boardCode}/article/${articleId}`, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(
            {
                title: updatedTitle,
                body: updatedContent,
            }
        )
    })
        .then(response => response.text())
        .then(data => {
            console.log(data);
        })
        .catch(error => {
            console.error('Error:', error);
        });

    const title = document.getElementById("title")
    title.setAttribute("disabled", "true");

    const content = document.getElementById("content")
    content.setAttribute("disabled", "true");

    const update = document.querySelector(".update")
    update.style.display = "block";
    const updateSend = document.querySelector(".updateSend")
    updateSend.style.display = "none";
}


//게시글 삭제 버튼
function deleteBtnClick() {
    if (confirm('정말로 삭제하시겠습니까?')) {
        location.href = '/board/ /1';
    } else {

    }
}

function windowHistoryBack() {
    const currentUrl = window.location.href;
    const urlParts = currentUrl.split('/');
    const boardId = urlParts[4];

    window.location.href = `/board/${boardId}/1`
}

//댓글 쓰기창
document.addEventListener("DOMContentLoaded", () => {
    const commentBtn = document.querySelector(".commentBtn");
    const commentAdd = document.querySelector(".commentAdd");

    commentBtn.addEventListener("click", () => {
        commentAdd.style.display = (commentAdd.style.display === "none") ? "block" : "none";
    });
});

//댓글 화면 출력
document.addEventListener("DOMContentLoaded", () => {

    const currentDate = new Date();
    const formattedDate = `${currentDate.getFullYear()}-${(currentDate.getMonth() + 1).toString().padStart(2, '0')}-${currentDate.getDate().toString().padStart(2, '0')}`;

    const commentValue = document.getElementById("commentAdd")
    const commentAddBtn = document.querySelector(".commentAddBtn")

    const commentList = document.querySelector(".commentList")

    commentAddBtn.addEventListener("click", () => {
        // var storedAccessToken = sessionStorage.getItem('accessToken');

        const storedAccessToken = getCookie('accessToken');
        let storedUname = getCookie('username');
        console.log(storedUname)
        // if (storedUname === undefined){
        //     storedUname = "댓글"
        // }

        // if (storedAccessToken === undefined) {
        //     alert("로그인이 필요한 서비스입니다.");
        //     return null;
        // } else {
            let userComment = commentValue.value;

            if (userComment !== "") {
                const comment =document.createElement("div")
                comment.className = "comment"
                commentList.appendChild(comment)

                const body = document.createElement("p")
                body.className = "body"
                body.textContent = storedUname + " | "+ userComment
                comment.append(body)

                const regDate = document.createElement("p")
                regDate.className = "regDate"
                regDate.textContent = formattedDate
                comment.append(regDate)

                const commentAdd = document.querySelector(".commentAdd")
                commentAdd.style.display = "none"
                commentSend(userComment)
            }
        // }
        commentValue.value = "";
    })

    commentValue.addEventListener("keypress", (event) => {
        if (event.key === "Enter") {
            event.preventDefault();
            commentAddBtn.click();
        }
    })
})

//쿠키 정보 가져오기
function getCookie(name) {
    const cookies = document.cookie.split(';');
    for (const cookie of cookies) {
        const [cookieName, cookieValue] = cookie.trim().split('=');
        if (cookieName === name) {
            return cookieValue;
        }
    }
    return null;
}

//댓글 전송
function commentSend(comment) {

    const currentUrl = window.location.href;
    const urlParts = currentUrl.split('/');
    const articleId = urlParts[4];
    console.log(articleId);

    console.log(comment)
    fetch(`/${articleId}/comment/new`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(
            {
                body: comment
            }
        )
    })
        .then(response => response.text())
        .then(result => {
            console.log(result);
        })
        .catch(error => console.error('Error:', error));

}


