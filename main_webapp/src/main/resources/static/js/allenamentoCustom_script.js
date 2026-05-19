let contatore = 1;

function aggiungiEsercizio() {
    // Ora peschiamo il contenitore giusto
    const container = document.getElementById("listaEsercizi");

    const newForm = document.createElement('div');
    newForm.classList.add('blocco-esercizi');
    newForm.style.marginTop = "15px";

    // ATTENZIONE QUI: Sto usando i backtick ( ` ) all'inizio e alla fine!
    newForm.innerHTML = `
        <hr>
        <label>Nome esercizio</label>
        <input type="text" name="esercizi[${contatore}].nome_esercizio" placeholder="Nome esercizio..." required>

        <label>Numero ripetizioni</label>
        <input type="number" name="esercizi[${contatore}].numero_ripetizioni" placeholder="Numero ripetizioni..." required>

        <label>Numero serie</label>
        <input type="number" name="esercizi[${contatore}].numero_serie" placeholder="Numero serie..." required>
    `;

    // Aggiungiamo il nuovo blocco alla fine della lista
    container.appendChild(newForm);

    // Incrementiamo per il prossimo
    contatore++;
}