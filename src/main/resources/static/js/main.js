document.addEventListener("DOMContentLoaded", () => {
    const carousel = document.getElementById("myCarousel");

    console.log(carousel)
    const numSlides = 10;

    const indicatorList = document.createElement("ul");
    indicatorList.classList.add("carousel-indicators");

    for (var i = 0; i < numSlides; i++) {
        const indicatorItem = document.createElement("li");
        indicatorItem.setAttribute("data-target", "#myCarousel");
        indicatorItem.setAttribute("data-slide-to", i);
        if (i === 0) {
            indicatorItem.classList.add("active");
        }
        indicatorList.appendChild(indicatorItem);
    }
    carousel.appendChild(indicatorList);


    const innerList = document.createElement("div")
    innerList.classList.add("carousel-inner")

    for (var j = 0; j < numSlides; j++) {
        const innerItem = document.createElement("div");
        if (j === 0) {
            innerItem.className="carousel-item active"
        }
        else {
            innerItem.className="carousel-item"
        }

        const itemContent = document.createElement("div")
        itemContent.className = "itemContent"

        const itemDesDiv = document.createElement("div")
        itemDesDiv.className = "itemDesDiv"

        const itemName = document.createElement("h2")
        itemName.className="item-name"
        itemName.textContent="자격증명" + j
        itemDesDiv.appendChild(itemName)

        const itemDescription = document.createElement("p")
        itemDescription.className="item-description"
        itemDescription.textContent="자격증에 대한 설명"
        itemDesDiv.appendChild(itemDescription)

        innerItem.appendChild(itemDesDiv);

        const itemImgDiv = document.createElement("div")
        itemImgDiv.className = "itemImgDiv"

        const itemImg = document.createElement("img")
        itemImg.className = "itemImg"
        itemImgDiv.appendChild(itemImg)

        itemContent.appendChild(itemDesDiv)
        itemContent.appendChild(itemImgDiv)

        innerItem.appendChild(itemContent)

        innerList.appendChild(innerItem)
    }
    carousel.appendChild(innerList);
})
