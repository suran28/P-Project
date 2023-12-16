function adminLoginForm() {
    const adminId = document.getElementById('adminId').value
    const password = document.getElementById('adminPw').value

    console.log(adminId, password)

    // 로그인 요청 보내기
    fetch('/admin/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(
            {
                userId: adminId,
                password: password
            }
        )
    })
        .then(res => res.json())
        .then(res => {
            const accessToken = res.accessToken;
            const username =res.username

            console.log(res)

            if (username === 'admin') {
                setCookie('accessToken', accessToken, 7);
                setCookie('username', username, 7)

                alert("로그인이 완료되었습니다.")

                window.location.href = "/admin/members"
            } else {
                alert("아이디 및 비밀번호를 다시 확인해주세요.");
                return null;
            }
        })
}

function setCookie(name, value, days) {
    const expires = new Date();
    expires.setTime(expires.getTime() + (days * 24 * 60 * 60 * 1000));
    document.cookie = `${name}=${value};expires=${expires.toUTCString()};path=/`;
}

function adminLogoutForm() {
    deleteCookie('accessToken');
    deleteCookie('username');
    window.location.href ="/admin"
}

function deleteCookie(name) {
    document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;';
}


