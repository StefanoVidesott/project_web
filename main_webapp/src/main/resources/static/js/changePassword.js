function validateChangePasswordForm() {
    const errorDiv = document.getElementById("errorContainer");
    errorDiv.style.display = "none"
    errorDiv.textContent = "";

    const newPwd = document.getElementById("newPassword").value;
    const confirmPwd = document.getElementById("confirmPassword").value;

    if (newPwd.length !== 8) {
        errorDiv.textContent = "La password deve essere lunga 8 caratteri.";
        errorDiv.style.display = "block"
        return false;
    }

    if (!newPwd.includes("id_07")) {
        errorDiv.textContent = "La password deve contenere 'id_07'.";
        errorDiv.style.display = "block"
        return false;
    }

    if (newPwd !== confirmPwd) {
        errorDiv.textContent = "Le due password non coincidono.";
        errorDiv.style.display = "block"
        return false;
    }

    return true;
}