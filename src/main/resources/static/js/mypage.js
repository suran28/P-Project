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
            const fieldCertRow = document.createElement("div");
            fieldCertRow.className = "fieldCertRow";
            fieldCert.appendChild(fieldCertRow);
        }

        const fieldCertRow = fieldCert.lastElementChild;

        const cert = document.createElement("div")
        cert.className = "cert"
        fieldCertRow.appendChild(cert)

        const certName = document.createElement("h4")
        certName.textContent = certInfo[i].certDetailDtos[j].certName
        cert.appendChild(certName)

        const certLink = document.createElement("a")
        certLink.href = certInfo[i].certDetailDtos[j].url
        certLink.textContent = "상세페이지로 이동"
        cert.appendChild(certLink)
    }
});
