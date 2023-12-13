function signUpForm() {

    const username = document.getElementById('username').value
    const userId = document.getElementById('userId').value
    const password = document.getElementById('password').value


    fetch('/signup', {
        method: 'post',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(
            {
                userId: userId,
                username: username,
                password: password,
            }
        )
    })
    .then(res => {
        if (res.status === 200) {
            console.log(res);
            window.confirm('회원가입이 완료 되었습니다!')
            window.location.href = "/login";
        } else if (res.status === 500) {
            window.confirm('이미 존재하는 아이디입니다!')
        }
    })
    .catch(error => {
        console.log("오류: ", error);
    })
}