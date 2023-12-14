document.addEventListener("DOMContentLoaded", () => {
    var addButton = document.querySelector(".addBtn button");
    var certAddWindow = document.querySelector(".certAddWindow");

    addButton.addEventListener("click", function () {
        certAddWindow.style.display = "block";
    });

    var closeButton = document.querySelector(".windowTitle button");
    closeButton.addEventListener("click", function () {
        certAddWindow.style.display = "none";
    });
});




// 페이지 로드 시 자격증 데이터 생성
document.addEventListener("DOMContentLoaded", () => {

    console.log(myCerts)

    const certInfoCon = document.querySelector(".certInfoCon")

    for (var j = 0; j < myCerts.length; j++){
        if (j % 5 === 0) {
            const certRow = document.createElement("div");
            certRow.className = "certRow";
            certInfoCon.appendChild(certRow);
        }

        const certRow = certInfoCon.lastElementChild;

        const cert = document.createElement("div")
        cert.className = "cert"
        certRow.appendChild(cert)

        const certName = document.createElement("p")
        certName.textContent = "| 자격증명 : " + myCerts[j].cert_name
        certName.className = "certName"
        cert.appendChild(certName)

        const certAuth = document.createElement("p")
        certAuth.textContent = "| 시험기관 : " + myCerts[j].host
        certAuth.className = "certAuth"
        cert.appendChild(certAuth)

        const certDate = document.createElement("p")
        certDate.textContent = "| 취득일 : " + myCerts[j].acq_date
        certDate.className = "certDate"
        cert.appendChild(certDate)

    }
});

//자격증 추가 창
document.addEventListener("DOMContentLoaded", () => {

    const certWindow = document.querySelector(".certWindow")

    for (let i = 0; i < myCerts.length; i++){
        const windowCertInfo = document.createElement("div")
        windowCertInfo.className = "windowCertInfo"
        certWindow.appendChild(windowCertInfo)

        const certId = document.createElement("p")
        certId.textContent = myCerts[i].id
        certId.className = "certId"
        certId.style.display = "none"
        windowCertInfo.appendChild(certId)

        const certTitle = document.createElement("input")
        certTitle.className = "certTitle"
        certTitle.value = myCerts[i].cert_name
        certTitle.disabled = true
        windowCertInfo.appendChild(certTitle)

        const certAuth = document.createElement("input")
        certAuth.className = "certAuth"
        certAuth.value = myCerts[i].host
        certAuth.disabled = true
        windowCertInfo.appendChild(certAuth)

        const certDate = document.createElement("input")
        certDate.className = "certDate"
        certDate.value = myCerts[i].acq_date
        // certDate.type = "date"
        certDate.disabled = true
        windowCertInfo.appendChild(certDate)

        const certUD = document.createElement("div")
        certUD.className = "certUD"
        windowCertInfo.appendChild(certUD)

        const certU = document.createElement("button")
        certU.className = "certU"
        certU.textContent = "수정"
        certUD.appendChild(certU)

        const sendCertU = document.createElement("button")
        sendCertU.className = "sendCertU"
        sendCertU.textContent = "완료"
        sendCertU.style.display = "none"
        certUD.appendChild(sendCertU)

        const certD = document.createElement("button")
        certD.className = "certD"
        certD.textContent = "삭제"
        certUD.appendChild(certD)
    }
})


//자격증 창 자격증 추가
document.addEventListener("DOMContentLoaded", () => {
    const certC = document.querySelector(".certC")

    certC.addEventListener("click", () => {
        var certTitleValue = document.getElementById('certTitle').value;
        var certAuthValue = document.getElementById('certAuth').value;
        var certDateValue = document.getElementById('certDate').value;

        if (certTitleValue === '' || certAuthValue === '' || certDateValue === '') {
            alert('입력란을 모두 채워주세요.');
        } else {
            const windowCertInfoList = document.querySelectorAll(".windowCertInfo");
            const listNum = windowCertInfoList.length

            let Id = 0
            if (listNum === 0){
                Id = 1
            } else {
                const selectedWindowCertInfo = windowCertInfoList[listNum - 1];
                const certIdElement = selectedWindowCertInfo.querySelector(".certId");
                Id = certIdElement.textContent + 1
            }

            const certWindow = document.querySelector(".certWindow")

            const windowCertInfo = document.createElement("div")
            windowCertInfo.className = "windowCertInfo"
            certWindow.appendChild(windowCertInfo)

            const certId = document.createElement("p")
            certId.textContent = Id
            certId.className = "certId"
            certId.style.display = "none"
            windowCertInfo.appendChild(certId)

            const certTitle = document.createElement("input")
            certTitle.className = "certTitle"
            certTitle.value = certTitleValue
            certTitle.disabled = true
            windowCertInfo.appendChild(certTitle)

            const certAuth = document.createElement("input")
            certAuth.className = "certAuth"
            certAuth.value = certAuthValue
            certAuth.disabled = true
            windowCertInfo.appendChild(certAuth)

            const certDate = document.createElement("input")
            certDate.className = "certDate"
            certDate.value = certDateValue
            certDate.disabled = true
            windowCertInfo.appendChild(certDate)

            const certUD = document.createElement("div")
            certUD.className = "certUD"
            windowCertInfo.appendChild(certUD)

            const certU = document.createElement("button")
            certU.className = "certU"
            certU.textContent = "수정"
            certUD.appendChild(certU)

            const sendCertU = document.createElement("button")
            sendCertU.className = "sendCertU"
            sendCertU.textContent = "완료"
            sendCertU.style.display = "none"
            certUD.appendChild(sendCertU)

            const certD = document.createElement("button")
            certD.className = "certD"
            certD.textContent = "삭제"
            certUD.appendChild(certD)

            document.getElementById('certTitle').value = ""
            document.getElementById('certAuth').value = ""
            document.getElementById('certDate').value = ""
        }
    })
})

//자격증 수정 버튼
document.addEventListener("DOMContentLoaded", () => {
    const certUList = document.querySelectorAll(".certU")

    certUList.forEach(function(certUElement) {
        certUElement.addEventListener('click', function() {

            const windowCertInfo = certUElement.closest(".windowCertInfo")

            const certTitleInput = windowCertInfo.querySelector(".certTitle");
            certTitleInput.removeAttribute("disabled");

            const certAuthInput = windowCertInfo.querySelector(".certAuth");
            certAuthInput.removeAttribute("disabled");

            const certDateInput = windowCertInfo.querySelector(".certDate");
            certDateInput.type = "date"
            certDateInput.removeAttribute("disabled");

            const certU = windowCertInfo.querySelector(".certU")
            certU.style.display = "none"

            const sendCertU = windowCertInfo.querySelector(".sendCertU")
            sendCertU.style.display = "block"


        });
    });
})

//자격증 수정 완료 - send
document.addEventListener("DOMContentLoaded", () => {
    const sendCertUList = document.querySelectorAll(".sendCertU")

    sendCertUList.forEach(function(sendCertUElement) {
        sendCertUElement.addEventListener('click', function() {

            const windowCertInfo = sendCertUElement.closest(".windowCertInfo")

            const certIdP = windowCertInfo.querySelector(".certId");
            const certId = certIdP.textContent

            const certTitleInput = windowCertInfo.querySelector(".certTitle");
            const certTitle = certTitleInput.value

            const certAuthInput = windowCertInfo.querySelector(".certAuth");
            const certAuth = certAuthInput.value

            const certDateInput = windowCertInfo.querySelector(".certDate");
            const certDate = certDateInput.value

            fetch(`/mypage/cert/${certId}`, {
                method: 'PATCH',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(
                    {
                        cert_name: certTitle,
                        host: certAuth,
                        acq_date: certDate,
                    }
                )
            })
                .then(res => res.json())
                .then(res => {
                    console.log(res)

                })
                .catch(error => {
                    console.error('Error:', error);
                });
        });
    });
})


//자격증 삭제
document.addEventListener("DOMContentLoaded", () => {
    const certDList = document.querySelectorAll(".certD")

    certDList.forEach(function(certDElement) {
        certDElement.addEventListener('click', function() {

            const windowCertInfo = certDElement.closest(".windowCertInfo")

            const certIdInput = windowCertInfo.querySelector(".certId");
            const certId = certIdInput.textContent

            windowCertInfo.remove();

            fetch(`/mypage/cert/${certId}`, {
                method: 'DELETE',
                    headers: {
                    'Content-Type': 'application/json',
                },
            })
                .then(res => res.text())
                .then(res => {
                    console.log(res)

                })
                .catch(error => {
                    console.error('Error:', error);
                });
        });
    });
})