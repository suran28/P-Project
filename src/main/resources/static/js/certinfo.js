

document.addEventListener("DOMContentLoaded", () => {

    const numCertInfo = certInfo.length

    for (var i = 0; i < numCertInfo; i++){
        const majorJobList = document.createElement("div")
    }
})

document.addEventListener("DOMContentLoaded", () => {
    var fieldSumButton = document.querySelector(".fieldSum button");
    var fieldCert = document.querySelector(".fieldCert");

    fieldSumButton.addEventListener("click", () => {
        fieldCert.style.display = (fieldCert.style.display === "none") ? "block" : "none";
    });
});