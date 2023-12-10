

//처음에 질문하는 챗 생성
document.addEventListener("DOMContentLoaded", () =>  {
    const aiChat = document.querySelector(".aiChat");
    const message = "안녕하세요 써트케어 서비스입니다 어떤 내용을 도와드릴까요?";
    const p = aiChat.querySelector("p");

    function showInitText(index) {
        p.textContent = message.slice(0, index);
        p.classList.add("show");
        aiChat.classList.add("show")

        if (index < message.length) {
            setTimeout(function () {
                showInitText(index + 1);
            }, 30);
        }
    }

    setTimeout(function () {
        showInitText(0);
    }, 100);
});


//클릭하여 질문을 화면에 표시
document.addEventListener("DOMContentLoaded", () =>  {
    const chatInput = document.getElementById("chatInput");
    const sendBtn = document.querySelector(".sendBtn");
    const memChatSave = document.querySelector(".ChatSave");

    sendBtn.addEventListener("click", function () {
        var storedAccessToken = localStorage.getItem('accessToken');

        if (storedAccessToken === undefined) {
            alert("로그인이 필요한 서비스입니다.");
            return null;
        } else {
            let userQuestion = chatInput.value;

            if (userQuestion !== "") {
                addMemChatToMemory(userQuestion, "chatSave");
                requestAi(userQuestion)
            }
        }

        chatInput.value = "";
    });

    chatInput.addEventListener("keypress", function (event) {
        if (event.key === "Enter") {
            event.preventDefault();
            sendBtn.click();
        }
    })
})



function addAiChatToMemory(message, chatSaveClass) {
    const chatSave = document.querySelector(`.${chatSaveClass}`);
    const newChat = document.createElement("div");
    newChat.classList.add("aiChat");
    const reChat = document.createElement("div");
    reChat.classList.add("aiChat");

    const aiAnswer = document.createElement("p");
    aiAnswer.textContent = message;

    const askMessage = "답변 내용에 만족하셨나요? 또 어떤 내용을 도와드릴까요?";
    const askUser = document.createElement("p");
    askUser.textContent = askMessage;

    function showAiText(index) {
        return new Promise(resolve => {
            aiAnswer.textContent = message.slice(0, index);
            aiAnswer.classList.add("show");
            newChat.classList.add("show")

            if (index < message.length) {
                setTimeout(function () {
                    showAiText(index + 1).then(resolve);
                }, 30);
            } else {
                resolve();
            }
        });
    }

    function requestUserFeedback(idx) {
        askUser.textContent = askMessage.slice(0, idx);
        askUser.classList.add("show");
        reChat.classList.add("show")

        return new Promise(resolve => {
            if (idx < askMessage.length) {
                setTimeout(function () {
                    requestUserFeedback(idx + 1).then(resolve);
                }, 30);
            } else {
                resolve();
            }
        });
    }

    showAiText(0).then(() => {
        return requestUserFeedback(0);
    })

    newChat.appendChild(aiAnswer);
    reChat.appendChild(askUser);
    chatSave.insertBefore(newChat, chatSave.firstChild);
    chatSave.insertBefore(reChat, chatSave.firstChild);
}



function addMemChatToMemory(message, chatSaveClass) {
    const chatSave = document.querySelector(`.${chatSaveClass}`);
    const newChat = document.createElement("div");
    newChat.classList.add("memChat");

    const p = document.createElement("p");
    p.textContent = message;

    newChat.appendChild(p);
    chatSave.insertBefore(newChat, chatSave.firstChild);
}

function requestAi(message) {
    console.log(message)
    fetch('/ask', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(
            {
                userRequest: message,
            }
        )
    })
        // .then(res => res.text())
        // .then(data => {
        //     console.log(data);
        //     addAiChatToMemory(data, "chatSave")
        // })
        // .catch(error => {
        //     console.error('Error:', error);
        // })

        .then(res => res.json())
        .then(res => {
            console.log(res)

            if (res.status === 500) {
                console.log("챗봇 응답:", res);
                let assistantResponse = "죄송합니다. 챗봇 답변을 불러오는데 실패했습니다."
                addAiChatToMemory(assistantResponse, "chatSave")
            } else {
                console.log("챗봇 응답:", res);
                console.log("챗봇 응답:", res.choices[0].message.content);
                let assistantResponse = res.choices[0].message.content
                addAiChatToMemory(assistantResponse, "chatSave")
            }
        })
        .catch(error => {
                console.error('Error:', error);
            });
}

function handleUserInput() {
    var storedAccessToken = localStorage.getItem('accessToken');

    if (storedAccessToken === null || storedAccessToken === undefined) {
        alert("로그인이 필요한 서비스입니다.");
        return;
    }

    let userQuestion = chatInput.value.trim();

    if (userQuestion !== "") {
        addMemChatToMemory(userQuestion, "chatSave");
        requestAi(userQuestion);

        memChatSave.scrollTop = memChatSave.scrollHeight;
    }

    chatInput.value = "";
}