function loginForm() {
    // const accessToken = sessionStorage.getItem("accessToken");
    var formData = {
        userId: document.getElementById('userId').value,
        password: document.getElementById('password').value
    };

    console.log(formData)

    // 로그인 요청 보내기
    fetch('/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(
            {
                userId: formData.userId,
                password: formData.password
            }
        )
    })
        .then(res => res.json())
        .then(res => {
            const accessToken = res.accessToken;
            console.log(res)

            if (res.accessToken === undefined) {
                alert("아이디 및 비밀번호를 다시 확인해주세요.");
                return null;
            } else {
                // sessionStorage.setItem('accessToken', accessToken);
                // console.log("로컬스토리지에 토큰 저장: ", accessToken);
                // sessionStorage.setItem("uname", res.uname);

                setCookie('accessToken', accessToken, 7);

                alert("로그인이 완료되었습니다.")
                window.location.href = '/';
            }
        })
}

function setCookie(name, value, days) {
    const expires = new Date();
    expires.setTime(expires.getTime() + (days * 24 * 60 * 60 * 1000));
    document.cookie = `${name}=${value};expires=${expires.toUTCString()};path=/`;
}