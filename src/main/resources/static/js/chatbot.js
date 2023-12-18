
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

    sendBtn.addEventListener("click", function () {

        let userQuestion = chatInput.value;

        if (userQuestion !== "") {
            addMemChatToMemory(userQuestion, "chatSave");
            requestAi(userQuestion)
        }

        chatInput.value = "";
    })

    chatInput.addEventListener("keypress", (event) => {
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
    newChat.appendChild(aiAnswer);

    const askMessage = "답변 내용에 만족하셨나요? 또 어떤 내용을 도와드릴까요?";
    const askUser = document.createElement("p");
    askUser.textContent = askMessage;
    reChat.appendChild(askUser);

    const satisfactionBtn = document.createElement("button");
    satisfactionBtn.textContent = "만족";
    satisfactionBtn.className = "satisfactionBtn"
    reChat.appendChild(satisfactionBtn);

    const dissatisfactionBtn = document.createElement("button");
    dissatisfactionBtn.textContent = "불만족";
    dissatisfactionBtn.className = "dissatisfactionBtn"
    reChat.appendChild(dissatisfactionBtn);

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

    // function requestUserFeedback(idx) {
    //     askUser.textContent = askMessage.slice(0, idx);
    //     askUser.classList.add("show")
    //     reChat.classList.add("show")
    //
    //     return new Promise(resolve => {
    //         if (idx < askMessage.length) {
    //             setTimeout(function () {
    //                 requestUserFeedback(idx + 1).then(resolve);
    //             }, 30);
    //         } else {
    //             resolve();
    //         }
    //     });
    // }

    function requestUserFeedback(idx) {
        askUser.textContent = askMessage.slice(0, idx);
        askUser.classList.add("show");
        reChat.classList.add("show");

        return new Promise(resolve => {
            if (idx < askMessage.length) {
                setTimeout(function () {
                    requestUserFeedback(idx + 1).then(() => {
                        // resolve()가 호출된 후에 실행되는 로직
                        satisfactionBtn.classList.add("show");
                        dissatisfactionBtn.classList.add("show");
                        resolve();
                    });
                }, 30);
            } else {
                resolve();
            }
        });
    }


    showAiText(0).then(() => {
        return requestUserFeedback(0);
    })

    chatSave.insertBefore(newChat, chatSave.firstChild);
    chatSave.insertBefore(reChat, chatSave.firstChild);

    // dissatisfactionBtn.classList.add("show")

    satiBtn()
    dissatiBtn()
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

//만족 버튼
function satiBtn() {
    const satiBtnList = document.querySelectorAll(".satisfactionBtn");

    satiBtnList.forEach(satiBtnElement => {
        satiBtnElement.addEventListener("click", function() {

            alert("반영이 완료되었습니다.");

            const aiChat = satiBtnElement.closest(".aiChat");
            const chatSave = aiChat.parentNode;

            const aiChatIndex = Array.from(chatSave.children).indexOf(aiChat);

            const aiAnswerDiv = chatSave.children[aiChatIndex + 1];
            const aiAnswer = aiAnswerDiv.querySelector("p").textContent;

            const userQuestionDiv = chatSave.children[aiChatIndex + 2];
            const userQuestion = userQuestionDiv.querySelector("p").textContent;

            console.log(aiAnswer)
            console.log(userQuestion);

            // fetch("/bad-answer", {
            //     method: "POST",
            //     headers: {
            //         "Content-Type": "application/json",
            //     },
            //     body: JSON.stringify(
            //         {
            //             question: userQuestion,
            //             answer: aiAnswer,
            //         }
            //     ),
            // })
            //     .then(res => {
            //         console.log(res)
            //         if (res.status === 200){
            //             alert("반영이 완료되었습니다.");
            //         }
            //         else if(res.status === 500){
            //             alert("로그인 후 이용해주시기 바랍니다.");
            //         }
            //     })
            //     .catch(error => {
            //         console.error(error);
            //     })
        })
    })
}

//불만족 버튼
function dissatiBtn() {
    const dissatiBtnList = document.querySelectorAll(".dissatisfactionBtn");

    dissatiBtnList.forEach(dissatiBtnElement => {
        dissatiBtnElement.addEventListener("click", function() {
            const aiChat = dissatiBtnElement.closest(".aiChat");
            const chatSave = aiChat.parentNode;

            const aiChatIndex = Array.from(chatSave.children).indexOf(aiChat);

            const aiAnswerDiv = chatSave.children[aiChatIndex + 1];
            const aiAnswer = aiAnswerDiv.querySelector("p").textContent;

            const userQuestionDiv = chatSave.children[aiChatIndex + 2];
            const userQuestion = userQuestionDiv.querySelector("p").textContent;

            console.log(aiAnswer)
            console.log(userQuestion);

            fetch("/bad-answer", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(
                    {
                        question: userQuestion,
                        answer: aiAnswer,
                    }
                ),
            })
                .then(res => {
                    console.log(res)
                    if (res.status === 200){
                        alert("반영이 완료되었습니다.");
                    }
                    else if(res.status === 500){
                        alert("로그인 후 이용해주시기 바랍니다.");
                    }
                })
                .catch(error => {
                    console.error(error);
                })
        })
    })
}
