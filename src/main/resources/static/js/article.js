// HTTP 요청을 보내는 함수
// function sendRequest(url, method, body = null) {
//     const accessToken = sessionStorage.getItem('accessToken');
//
//     fetch(url, {
//         method: method,
//         headers: {
//             'Authorization': `Bearer ${accessToken}`,
//         },
//         body: body ? JSON.stringify(body) : null,
//     })
//         .then(response => response.json())
//         .then(data => console.log(data))
//         .catch(error => console.error('Error:', error));
// }
//
// sendRequest('/api/data', 'GET');

document.addEventListener("DOMContentLoaded", () => {
    const updateBtn = document.querySelector(".updateBtn")


    // console.log(writerChk)
    // (if writerChk === true){
    //
    // }
})

document.addEventListener("DOMContentLoaded", () => {
    const commentBtn = document.querySelector(".commentBtn");
    const commentAdd = document.querySelector(".commentAdd");

    commentBtn.addEventListener("click", () => {
        commentAdd.style.display = (commentAdd.style.display === "none") ? "block" : "none";
    });
});


document.addEventListener("DOMContentLoaded", () => {

    const currentDate = new Date();
    const formattedDate = `${currentDate.getFullYear()}-${(currentDate.getMonth() + 1).toString().padStart(2, '0')}-${currentDate.getDate().toString().padStart(2, '0')}`;


    const commentValue = document.getElementById("commentAdd")
    const commentAddBtn = document.querySelector(".commentAddBtn")

    const commentList = document.querySelector(".commentList")

    commentAddBtn.addEventListener("click", () => {
        var storedAccessToken = sessionStorage.getItem('accessToken');
        var storedUname = sessionStorage.getItem("uname")
        if (storedUname === undefined){
            storedUname = "유저명이 안오고 있네"
        }

        if (storedAccessToken === undefined) {
            alert("로그인이 필요한 서비스입니다.");
            return null;
        } else {
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
                commentSend(userComment, storedAccessToken)
            }
        }

        commentValue.value = "";

    })

    commentValue.addEventListener("keypress", (event) => {
        if (event.key === "Enter") {
            event.preventDefault();
            commentAddBtn.click();
        }
    })
})


function commentSend(comment, accessToken) {
    const currentUrl = window.location.href;
    const urlParts = currentUrl.split('/');
    const articleId = urlParts[4];
    console.log(articleId);

    console.log(comment)
    fetch(`/${articleId}/comment/new`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${accessToken}`,
        },
        body: JSON.stringify(
            {
                body: comment,
            }
        )
    })

}


