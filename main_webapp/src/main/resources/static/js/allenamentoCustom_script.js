let contatore = 1;

function aggiungiEsercizio() {
    const container = document.getElementById("listaEsercizi");

    const selectOriginale = document.querySelector('select[name="esercizi[0].nome_esercizio"]');

    const newForm = document.createElement('div');
    newForm.classList.add('blocco-esercizi');
    newForm.style.marginTop = "15px";

    newForm.innerHTML = `
        <hr>
        <label>Nome esercizio</label>
         <select class="form-select" aria-label="Default select example" name="esercizi[${contatore}].nome_esercizio" required>
                     ${selectOriginale.innerHTML}
                 </select>

        <label>Numero ripetizioni</label>
        <input type="number" name="esercizi[${contatore}].numero_ripetizioni" placeholder="Numero ripetizioni..." required>

        <label>Numero serie</label>
        <input type="number" name="esercizi[${contatore}].numero_serie" placeholder="Numero serie..." required>
    `;

    container.appendChild(newForm);

    contatore++;
}