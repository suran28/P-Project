document.addEventListener("DOMContentLoaded", () =>  {
    const chatsContainer = document.querySelector('.chatCon');

    chats.forEach(chat => {
        const chatDiv = document.createElement('div');
        chatDiv.classList.add('chat');

        const chatQuestion = document.createElement('p');
        chatQuestion.classList.add('chatQuestion')
        chatQuestion.textContent = chat.question
        chatDiv.appendChild(chatQuestion)

        const chatAnswer = document.createElement('p');
        chatAnswer.classList.add('chatAnswer')
        chatAnswer.textContent = chat.answer
        chatDiv.appendChild(chatAnswer)

        const username = document.createElement('p');
        username.classList.add('username')
        username.textContent = chat.username
        chatDiv.appendChild(username)

        const chatRegDate = document.createElement('p');
        chatRegDate.classList.add('chatRegDate')
        chatRegDate.textContent = chat.username
        chatDiv.appendChild(chatRegDate)

        chatsContainer.appendChild(chatDiv)
    });

})