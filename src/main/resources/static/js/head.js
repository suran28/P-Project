// document.addEventListener("DOMContentLoaded", () => {
//     const userStatus = document.querySelector(".userStatus")
//
//     const storedAccessToken = sessionStorage.getItem('accessToken')
//
//     console.log(storedAccessToken)
//     if (storedAccessToken === null) {
//         // const loginBtn = document.createElement('button')
//         const loginLink = document.createElement('a')
//         loginLink.href = "/login"
//         loginLink.textContent = "로그인"
//         userStatus.appendChild(loginLink)
//         const signupLink = document.createElement('a')
//         signupLink.href = "/signup"
//         signupLink.textContent = "회원가입"
//         userStatus.appendChild(signupLink)
//
//     } else {
//         const logoutBtn = document.createElement('button')
//         logoutBtn.className = "logoutBtn"
//         logoutBtn.textContent = "로그아웃"
//         userStatus.appendChild(logoutBtn)
//         const mypageLink = document.createElement('a')
//         mypageLink.href = "/mypage"
//         mypageLink.textContent = "마이페이지"
//         userStatus.appendChild(mypageLink)
//
//         logoutBtn.addEventListener("click", () => {
//             console.log("로그아웃");
//             sessionStorage.removeItem('accessToken');
//             window.location.href = '/';
//         });
//     }
// })



document.addEventListener("DOMContentLoaded", () => {
    const storedAccessToken = sessionStorage.getItem('accessToken');

    const notAccessedContainer = document.querySelector(".notAccessed");
    const accessedContainer = document.querySelector(".accessed");
    const logoutBtn = document.querySelector(".logoutBtn");

    if (storedAccessToken === null) {
        notAccessedContainer.style.display = "block";
        accessedContainer.style.display = "none";
    } else {
        notAccessedContainer.style.display = "none";
        accessedContainer.style.display = "block";

        logoutBtn.addEventListener("click", () => {
            console.log("로그아웃");
            sessionStorage.removeItem('accessToken');
            window.location.href = '/';
        });
    }

})