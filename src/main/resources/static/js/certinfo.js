document.addEventListener("DOMContentLoaded", () => {
    var fieldSumButton = document.querySelector(".fieldSum button");
    var fieldCert = document.querySelector(".fieldCert");

    // fieldSumButton.addEventListener("click", function () {
    //     toggleCertAddWindow();
    // });
    //
    // function toggleCertAddWindow() {
    //     if (fieldCert.style.display === "none" || fieldCert.style.display === "") {
    //         fieldCert.style.display = "block";
    //     } else {
    //         fieldCert.style.display = "none";
    //     }
    // }

    fieldSumButton.addEventListener("click", () => {
        fieldCert.style.display = (fieldCert.style.display === "none") ? "block" : "none";
    });
});