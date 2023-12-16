document.addEventListener("DOMContentLoaded", () =>  {
    const membersContainer = document.querySelector('.memberCon');

    members.forEach(member => {
        const memberDiv = document.createElement('div');
        memberDiv.classList.add('member');

        const memberId = document.createElement('p');
        memberId.classList.add('memberID')
        memberId.textContent = member.id
        memberDiv.appendChild(memberId)

        const userId = document.createElement('p');
        userId.classList.add('memberId')
        userId.textContent = member.userId
        memberDiv.appendChild(userId)

        const username = document.createElement('p');
        username.classList.add('memberName')
        username.textContent = member.username
        memberDiv.appendChild(username)

        membersContainer.appendChild(memberDiv)
    });

})