function search(event){
    symbol = document.getElementById('user_symbol').value;
    if (symbol != ""){
        document.cookie = "consult="+symbol;
        window.location.replace("/stock.html");
    }   
}

document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("user_button").addEventListener("click", search);
});