function submitForm() {
    var formData = {
        username: document.getElementById('username').value,
        userId: document.getElementById('userId').value,
        password: document.getElementById('password').value
    };

    console.log(formData)

    fetch('/signup', {
        method: 'post',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(
            {
                userId: formData.userId,
                userName: formData.userName,
                password: formData.password,
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