document.addEventListener("DOMContentLoaded", () =>  {
    const posts = [
        {
            title: "뭐라고 해야 좋을까",
            writer: "작성자1",
            date: "작성일1",
            link: "/1"
        },
        {
            title: "다른 게시글",
            writer: "작성자2",
            date: "작성일2",
            link: "/2"
        },
        {
            title: "뭐라고 해야 좋을까",
            writer: "작성자1",
            date: "작성일1",
            link: "/1"
        },
        {
            title: "다른 게시글",
            writer: "작성자2",
            date: "작성일2",
            link: "/2"
        },
        {
            title: "뭐라고 해야 좋을까",
            writer: "작성자1",
            date: "작성일1",
            link: "/1"
        },
        {
            title: "다른 게시글",
            writer: "작성자2",
            date: "작성일2",
            link: "/2"
        },
        {
            title: "뭐라고 해야 좋을까",
            writer: "작성자1",
            date: "작성일1",
            link: "/1"
        },
        {
            title: "다른 게시글",
            writer: "작성자2",
            date: "작성일2",
            link: "/2"
        },
        {
            title: "뭐라고 해야 좋을까",
            writer: "작성자1",
            date: "작성일1",
            link: "/1"
        },
        {
            title: "다른 게시글",
            writer: "작성자2",
            date: "작성일2",
            link: "/2"
        },
        {
            title: "뭐라고 해야 좋을까",
            writer: "작성자1",
            date: "작성일1",
            link: "/1"
        },
        {
            title: "다른 게시글",
            writer: "작성자2",
            date: "작성일2",
            link: "/2"
        },
    ];

    const postsPerPage = 10;

    const currentPageNumber = parseInt(window.location.pathname.split('/').pop(), 10);
    const parsedValue = currentPageNumber

    const startIndex = (parsedValue - 1) * postsPerPage;
    const endIndex = startIndex + postsPerPage - 1;

    const boardCon = document.querySelector('.boardCon');

    for (let i = startIndex; i <= endIndex && i < posts.length; i++) {
        const post = posts[i];

        const newBoard = document.createElement('div');
        newBoard.classList.add('board');

        const titleLink = document.createElement('a');
        titleLink.href = post.link;
        titleLink.classList.add('title');
        titleLink.innerHTML = `<p>${post.title}</p>`;

        const writerElement = document.createElement('p');
        writerElement.classList.add('writer');
        writerElement.textContent = post.writer;

        const dateElement = document.createElement('p');
        dateElement.classList.add('date');
        dateElement.textContent = post.date;

        newBoard.appendChild(titleLink);
        newBoard.appendChild(writerElement);
        newBoard.appendChild(dateElement);

        boardCon.appendChild(newBoard);
    }


    const container = document.querySelector('.container');


    const pageItem = document.createElement("div")
    pageItem.classList.add('paging');

    const paging = posts.length % postsPerPage
    console.log(paging)

    for (let i =0; i < paging; i++){
        const pageItemList = document.createElement('li')

        const pageLink = document.createElement('a')
        pageLink.href = `/board/${i+1}`
        pageLink.textContent = i+1;

        pageItemList.appendChild(pageLink);

        pageItem.appendChild(pageItemList);
    }
    container.appendChild(pageItem);


    const emptyDiv = document.createElement('div');

    emptyDiv.classList.add('empty');
    container.appendChild(emptyDiv);
})

