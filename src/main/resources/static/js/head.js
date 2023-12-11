document.addEventListener("DOMContentLoaded", () => {
    const userStatus = document.querySelector(".userStatus")

    const storedAccessToken = localStorage.getItem('accessToken')

    console.log(storedAccessToken)
    if (storedAccessToken === null) {
        // const loginBtn = document.createElement('button')
        const loginLink = document.createElement('a')
        loginLink.href = "/login"
        loginLink.textContent = "로그인"
        userStatus.appendChild(loginLink)
        const signupLink = document.createElement('a')
        signupLink.href = "/signup"
        signupLink.textContent = "회원가입"
        userStatus.appendChild(signupLink)

    } else {
        const logoutBtn = document.createElement('button')
        logoutBtn.className = "logoutBtn"
        logoutBtn.textContent = "로그아웃"
        userStatus.appendChild(logoutBtn)
        const mypageLink = document.createElement('a')
        mypageLink.href = "/mypage"
        mypageLink.textContent = "마이페이지"
        userStatus.appendChild(mypageLink)

        logoutBtn.addEventListener("click", () => {
            console.log("로그아웃");
            localStorage.removeItem('accessToken');
            window.location.href = '/main';
        });
    }
})