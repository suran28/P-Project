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