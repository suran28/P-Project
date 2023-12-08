// chatbot.js

// 질문 입력과 버튼 클릭 이벤트 처리
document.addEventListener("DOMContentLoaded", function () {
    const chatInput = document.getElementById("chatInput");
    const sendBtn = document.querySelector(".sendBtn");
    const memChatSave = document.querySelector(".ChatSave");

    sendBtn.addEventListener("click", function () {
        const userQuestion = chatInput.value;

        console.log("클릭")

        // 질문을 채팅 목록에 추가
        addChatToMemory(userQuestion, "memChatSave");

        // 여기에 챗봇의 응답 로직을 추가할 수 있습니다.
        // 챗봇의 응답을 얻어오는 함수 등을 호출하면 됩니다.

        // 입력창 초기화
        chatInput.value = "";
    });
});

// 채팅 목록에 새로운 채팅 추가
function addChatToMemory(message, chatSaveClass) {
    const chatSave = document.querySelector(`.${chatSaveClass}`);
    const newChat = document.createElement("div");
    newChat.classList.add("memChatSave");

    const p = document.createElement("p");
    p.textContent = message;

    newChat.appendChild(p);
    chatSave.appendChild(newChat);
}
