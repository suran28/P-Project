document.addEventListener("DOMContentLoaded", () =>  {
    const postsPerPage = 10;

    const currentPageNumber = parseInt(window.location.pathname.split('/').pop(), 10);
    const parsedValue = currentPageNumber

    const startIndex = (parsedValue - 1) * postsPerPage;
    const endIndex = startIndex + postsPerPage - 1;

    const boardCon = document.querySelector('.boardCon');

    for (let i = startIndex; i <= endIndex && i < articleList.length; i++) {
        const post = articleList[i];

        const newBoard = document.createElement('div');
        newBoard.classList.add('board');

        const titleLink = document.createElement('a');
        titleLink.href = post.url;
        titleLink.classList.add('articleTitle');
        titleLink.innerHTML = `<p>${post.title}</p>`;

        const writerElement = document.createElement('p');
        writerElement.classList.add('writer');
        writerElement.textContent = post.writer;

        const regDateElement = document.createElement('p');
        regDateElement.classList.add('regDate');
        regDateElement.textContent = post.regDate;

        newBoard.appendChild(titleLink);
        newBoard.appendChild(writerElement);
        newBoard.appendChild(regDateElement);

        boardCon.appendChild(newBoard);
    }

    const currentUrl = window.location.href;
    const urlParts = currentUrl.split('/');
    const boardtype = urlParts[4];

    const container = document.querySelector('.container');

    const pageItem = document.createElement("div")
    pageItem.classList.add('paging');

    const paging = articleList.length % postsPerPage
    console.log(paging)

    for (let i =0; i < paging; i++){
        const pageItemList = document.createElement('li')

        const pageLink = document.createElement('a')
        pageLink.href = `/board/${boardtype}/${i+1}`
        pageLink.textContent = i+1;

        pageItemList.appendChild(pageLink);

        pageItem.appendChild(pageItemList);
    }
    container.appendChild(pageItem)

})

document.addEventListener("DOMContentLoaded", () =>{
    const storedAccessToken = getCookie('accessToken');
    if (storedAccessToken === undefined) {
        alert("로그인이 필요한 서비스입니다.");
        return null;
    }
})


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

document.addEventListener("DOMContentLoaded", () =>{
    const currentUrl = window.location.href;
    const urlParts = currentUrl.split('/');
    const boardId = urlParts[4];

    const write = document.querySelector(".write")
    write.href = `/board/${boardId}/article/new`
})