document.addEventListener("DOMContentLoaded", function () {
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
    const jsonData = [
        { certName: "토익", certDate: "2023-12-31" },
        { certName: "토익", certDate: "2023-12-31" },
        { certName: "토익", certDate: "2023-12-31" },
        { certName: "토익", certDate: "2023-12-31" },
        { certName: "토익", certDate: "2023-12-31" },
    ];

    const certInfoCon = document.querySelector(".certInfoCon")

    for (const certData of jsonData){
        if (cerData%5 !== 4){
            const certRow = document.createElement("div")
            certRow.className = "certRow";

        //     for (let i = 0; i++) {
        //         const certDiv = document.createElement("div");
        //
        //         certDiv.className = "cert";
        //
        //         certDiv.innerHTML = `
        //   <p class="certName">| 자격증명 : ${certData.certName}</p>
        //   <p class="certDate">| 취득일 : ${certData.certDate}</p>
        // `;
        //         certRowContainer.appendChild(certDiv);
        //     }
        }
    }
});
