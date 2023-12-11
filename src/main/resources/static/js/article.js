// HTTP 요청을 보내는 함수
function sendRequest(url, method, body = null) {
    const accessToken = localStorage.getItem('accessToken');

    fetch(url, {
        method: method,
        headers: {
            'Authorization': `Bearer ${accessToken}`,
        },
        body: body ? JSON.stringify(body) : null,
    })
        .then(response => response.json())
        .then(data => console.log(data))
        .catch(error => console.error('Error:', error));
}

// 사용 예시
sendRequest('/api/data', 'GET');



document.addEventListener("DOMContentLoaded", () => {
    const commentBtn = document.querySelector(".commentBtn");
    const commentAdd = document.querySelector(".commentAdd");

    commentBtn.addEventListener("click", () => {
        commentAdd.style.display = (commentAdd.style.display === "none") ? "block" : "none";
    });
});


document.addEventListener("DOMContentLoaded", () => {
    const commentValue = document.getElementById("commentAdd")
    const commentAddBtn = document.querySelector(".commentAddBtn")

    const commentList = document.querySelector(".commentList")

    commentAddBtn.addEventListener("click", () => {
        var storedAccessToken = localStorage.getItem('accessToken');

        if (storedAccessToken === undefined) {
            alert("로그인이 필요한 서비스입니다.");
            return null;
        } else {
            let userComment = commentValue.value;

            if (userComment !== "") {
                const comment = document.createElement("p")
                comment.textContent = "댓글 | " + userComment
                commentList.append(comment)

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
            // 'Content-Type': 'application/json',
            'Authorization': `Bearer ${accessToken}`,
        },
        body: JSON.stringify(
            {
                body: comment,
            }
        )
    })
        .then(res => res.text())
        .then(data => {
            console.log(data);
            addAiChatToMemory(data, "chatSave")
        })
        .catch(error => {
            console.error('Error:', error);
        })

    // .then(res => res.json())
    // .then(res => {
    //     console.log(res)
    //
    //     if (res.status === 500) {
    //         console.log("챗봇 응답:", res);
    //         let assistantResponse = "죄송합니다. 챗봇 답변을 불러오는데 실패했습니다."
    //         addAiChatToMemory(assistantResponse, "chatSave")
    //     } else {
    //         console.log("챗봇 응답:", res);
    //         console.log("챗봇 응답:", res.choices[0].message.content);
    //         let assistantResponse = res.choices[0].message.content
    //         addAiChatToMemory(assistantResponse, "chatSave")
    //     }
    // })
    // .catch(error => {
    //         console.error('Error:', error);
    //     });
}


