function validateSignUpForm() {
    const errorDiv = document.getElementById("errorContainer");
    errorDiv.textContent = "";

    // BirthDate validation GG/MM/AAAA
    const dateInput = document.getElementById("birthDateDisplay").value.trim();
    const dateRegex = /^(\d{2})\/(\d{2})\/(\d{4})$/;
    const match = dateInput.match(dateRegex);
    if (!match) {
        errorDiv.textContent = "Formato data non valido. Usa GG/MM/AAAA.";
        errorDiv.style.display = "block"
        return false;
    }

    const day   = parseInt(match[1]);
    const month = parseInt(match[2]);
    const year  = parseInt(match[3]);
    const birthDate = new Date(year, month - 1, day);

    if (birthDate.getFullYear() !== year ||
        birthDate.getMonth() !== month - 1 ||
        birthDate.getDate() !== day) {
        errorDiv.textContent = "Data di nascita non valida.";
        errorDiv.style.display = "block"
        return false;
    }

    const today = new Date();
    const age18 = new Date(birthDate);
    age18.setFullYear(age18.getFullYear() + 18);
    if (age18 > today) {
        errorDiv.textContent = "Devi essere maggiorenne per registrarti.";
        errorDiv.style.display = "block"
        return false;
    }

    // Convertto YYYY-MM-DD (Java compatible)
    const yyyy = year.toString().padStart(4, "0");
    const mm   = month.toString().padStart(2, "0");
    const dd   = day.toString().padStart(2, "0");
    document.getElementById("birthDate").value = yyyy + "-" + mm + "-" + dd;

    // Password validation
    const pwd1 = document.getElementById("password1").value;
    if (pwd1.length < 8) {
        errorDiv.textContent = "La password deve essere lunga almeno 8 caratteri.";
        errorDiv.style.display = "block"
        return false;
    }
    if (!pwd1.includes("id_07")) {
        errorDiv.textContent = "La password deve contenere 'id_07'.";
        errorDiv.style.display = "block"
        return false;
    }

    const pwd2 = document.getElementById("password2").value;
    if (pwd1 !== pwd2) {
        errorDiv.textContent = "Le due password non coincidono.";
        errorDiv.style.display = "block"
        return false;
    }

    return true;
}