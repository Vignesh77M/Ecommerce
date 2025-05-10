function addToCart(productId) {
    fetch(`/cart/add/${productId}`, { method: 'POST' })
        .then(response => response.text())
        .then(data => alert("Added to Cart!"))
        .catch(error => console.error("Error:", error));
}

function removeFromCart(productId) {
    fetch(`/cart/remove/${productId}`, { method: 'POST' })
        .then(response => response.text())
        .then(data => alert("Removed from Cart!"))
        .catch(error => console.error("Error:", error));
}
