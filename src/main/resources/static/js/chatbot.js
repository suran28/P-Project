

//처음에 질문하는 챗 생성
document.addEventListener("DOMContentLoaded", () =>  {
    const aiChat = document.querySelector(".aiChat");
    const message = "안녕하세요 써트케어 서비스입니다 어떤 내용을 도와드릴까요?";
    const p = aiChat.querySelector("p");

    function showText(index) {
        p.textContent = message.slice(0, index);
        p.classList.add("show");

        if (index < message.length) {
            setTimeout(function () {
                showText(index + 1);
            }, 50);
        }
    }

    setTimeout(function () {
        showText(0);
    }, 500);
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
            const userQuestion = chatInput.value;
            addChatToMemory(userQuestion, "chatSave");
        }
        chatInput.value = "";
    });
});

function addChatToMemory(message, chatSaveClass) {
    const chatSave = document.querySelector(`.${chatSaveClass}`);
    const newChat = document.createElement("div");
    newChat.classList.add("memChat");

    const p = document.createElement("p");
    p.textContent = message;

    newChat.appendChild(p);
    chatSave.insertBefore(newChat, chatSave.firstChild);
}
