

document.addEventListener("DOMContentLoaded", () => {

    const numCertInfo = certInfo.length

    const certCon = document.querySelector(".certCon")

    for (var i = 0; i < numCertInfo; i++){
        const majorJobList = document.createElement("div")

        const field = document.createElement("div")
        field.className = "field"
        certCon.appendChild(field)

        const fieldSum = document.createElement("div")
        fieldSum.className = "fieldSum"
        field.appendChild(fieldSum)

        const jobMajorName = document.createElement("h4")
        jobMajorName.textContent = certInfo[i].majorJobName
        fieldSum.appendChild(jobMajorName)

        const jobMajorBtn = document.createElement("button")
        jobMajorBtn.type = "button"
        jobMajorBtn.textContent = "더보기"
        fieldSum.appendChild(jobMajorBtn)

        console.log(certInfo[i].certDetailDtos)
        const fieldCert = document.createElement("div")
        fieldCert.className = "fieldCert"
        field.appendChild(fieldCert)

        for (var j = 0; j < certInfo[i].certDetailDtos.length; j++){
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
    }
})

document.addEventListener("DOMContentLoaded", () => {
    var fieldSumButtons = document.querySelectorAll(".fieldSum button");

    fieldSumButtons.forEach(button => {
        button.addEventListener("click", (event) => {
            console.log(event.target);
            const field = event.target.closest(".field");
            if (field) {
                const fieldCert = field.querySelector(".fieldCert");

                if (fieldCert) {
                    fieldCert.style.display = (fieldCert.style.display === "none") ? "block" : "none";
                }
            }
        });
    });
});