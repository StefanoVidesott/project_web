function validateLoginForm() {
    const errorDiv = document.getElementById("errorContainer");
    errorDiv.style.display = "none"
    errorDiv.textContent = "";

    const psw = document.getElementById("password").value;

    if (psw.length !== 8) {
        errorDiv.textContent = "La password deve essere lunga 8 caratteri.";
        errorDiv.style.display = "block"
        return false;
    }

    if (!psw.includes("id_07")) {
        errorDiv.textContent = "La password deve contenere 'id_07'.";
        errorDiv.style.display = "block"
        return false;
    }

    return true;
}