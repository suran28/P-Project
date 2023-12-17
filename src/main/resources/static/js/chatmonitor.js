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
        const dateTime = new Date(chat.regDate);
        const dateOnly = new Date(dateTime.getFullYear(), dateTime.getMonth(), dateTime.getDate());
        const formattedDate = dateOnly.toISOString().split('T')[0];
        chatRegDate.textContent = formattedDate
        chatDiv.appendChild(chatRegDate)

        chatsContainer.appendChild(chatDiv)
    });

})